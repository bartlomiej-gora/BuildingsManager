/**
 * 
 */
package pl.bgora.actions;

import com.github.bgora.beans.entity.Attachement;
import com.github.bgora.beans.entity.Message;
import com.github.bgora.beans.session.bussiness.MessageBeanService;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DownloadAction;
import pl.bgora.utils.ActionParams;
import pl.bgora.utils.ActionTools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Akcja pobieraj�ca plik z za�a�znika.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class GetAttachementAction extends DownloadAction {

	private MessageBeanService messageService;

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see org.apache.struts.actions.DownloadAction#getStreamInfo(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected StreamInfo getStreamInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception { // NOSONAR
		if (ActionTools.paramExists(request, ActionParams.MESSAGE_ID)
				&& ActionTools.paramExists(request,
						ActionParams.ATTACHEMENT_NAME)) {
			String messId = request.getParameter(ActionParams.MESSAGE_ID);
			Long id = Long.valueOf(messId);
			String attachName = request
					.getParameter(ActionParams.ATTACHEMENT_NAME);
			Message mess = messageService.getById(id);
			List<Attachement> attachements = mess.getAttachements();
			Attachement selected = null;
			for (Attachement attach : attachements) {
				if (attach.getFilename().equals(attachName)) {
					selected = attach;
					break;
				}
			}
			String format = selected.getFormat();
			byte[] content = selected.getContent();
			return new ByteStreamInfo(format, content);
		}

		return null;
	}

	/**
	 * Zwraca warto�� pola messageService
	 * 
	 * @return Warto�� pola messageService
	 */
	public MessageBeanService getMessageService() {
		return messageService;
	}

	/**
	 * Ustawia warto�� pola messageService
	 * 
	 * @param messageService
	 *            Nowa warto�� pola messageService
	 */
	public void setMessageService(MessageBeanService messageService) {
		this.messageService = messageService;
	}

	protected class ByteStreamInfo implements StreamInfo {

		private final String contentType;
		private final byte[] content;

		public ByteStreamInfo(String contentType, byte[] content) {
			this.contentType = contentType;
			this.content = content;
		}

		@Override
		public String getContentType() {
			return contentType;
		}

		@Override
		public InputStream getInputStream() throws IOException {
			return new ByteArrayInputStream(content);
		}

	}

}
