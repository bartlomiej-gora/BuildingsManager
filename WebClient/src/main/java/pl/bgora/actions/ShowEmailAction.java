/**
 * 
 */
package pl.bgora.actions;

import com.github.bgora.beans.entity.Message;
import com.github.bgora.beans.session.bussiness.MessageBeanService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import pl.bgora.forms.ShowEmailForm;
import pl.bgora.utils.ActionParams;
import pl.bgora.utils.ActionResult;
import pl.bgora.utils.ActionTools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Akcja podgl�du wiadomo�ci email.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class ShowEmailAction extends Action {

	private MessageBeanService messageService;

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception { // NOSONAR
		ShowEmailForm emailForm = (ShowEmailForm) form;
		if (ActionTools.paramExists(request, ActionParams.ID)) {
			String sId = request.getParameter(ActionParams.ID);
			Long id = Long.valueOf(sId);
			Message message = messageService.getById(id);
			emailForm.setAttachements(message.getAttachements());
			emailForm.setFrom(message.getFrom().getLogin());
			emailForm.setTitle(message.getTitle());
			emailForm.setContent(message.getContent());
			emailForm.setMessage(message);
			return mapping.findForward(ActionResult.SUCCESS);
		} else if (ActionTools.buttonHit(request, ActionParams.CANCEL)) {
			return mapping.findForward(ActionResult.BACK);
		}
		return mapping.findForward(ActionResult.SUCCESS);
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

	/**
	 * Zwraca warto�� pola messageService
	 * 
	 * @return Warto�� pola messageService
	 */
	public MessageBeanService getMessageService() {
		return messageService;
	}
}
