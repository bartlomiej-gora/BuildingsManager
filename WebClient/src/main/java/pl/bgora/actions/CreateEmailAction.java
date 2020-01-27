/**
 * 
 */
package pl.bgora.actions;

import com.github.bgora.beans.entity.Attachement;
import com.github.bgora.beans.entity.Message;
import com.github.bgora.beans.entity.User;
import com.github.bgora.beans.session.bussiness.MessageBeanService;
import com.github.bgora.beans.session.bussiness.UserBeanService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import pl.bgora.forms.CreateEmailForm;
import pl.bgora.utils.ActionParams;
import pl.bgora.utils.ActionResult;
import pl.bgora.utils.ActionTools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Akcja tworzenia wiadomo�ci email.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class CreateEmailAction extends Action {

	private MessageBeanService messageService;

	private UserBeanService userService;

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
			throws Exception {
		if (ActionTools.buttonHit(request, ActionParams.CANCEL)) {
			return mapping.findForward(ActionResult.BACK);
		}
		CreateEmailForm emailForm = (CreateEmailForm) form; // NOSONAR
		if (ActionTools.buttonHit(request, ActionParams.ADD_FILE)) {
			FormFile file = emailForm.getAttachement();
			Attachement attach = new Attachement();
			attach.setFilename(file.getFileName());
			attach.setFormat(file.getContentType());
			attach.setContent(file.getFileData());
			List<Attachement> attachements = emailForm.getAttachements();
			attachements.add(attach);
			emailForm.setAttachements(attachements);
			emailForm.setAttachement(null);
			return mapping.findForward(ActionResult.SUCCESS);
		} else if (ActionTools
				.paramEquals(request, ActionParams.REMOVE, "true")) {
			String attachName = request
					.getParameter(ActionParams.ATTACHEMENT_NAME);
			Attachement attach = new Attachement();
			attach.setFilename(attachName);
			emailForm.getAttachements().remove(attach);
			return mapping.findForward(ActionResult.SUCCESS);
		} else if (ActionTools.buttonHit(request, ActionParams.SUBMIT)) {
			Message message = new Message();
			message.setAttachements(emailForm.getAttachements());
			message.setTitle(emailForm.getTitle());
			message.setContent(emailForm.getContent());
			User current = (User) request.getSession().getAttribute(
					ActionParams.CURRENT_USER);
			message.setFrom(current);
			User to = userService.getUserByLogin(emailForm.getChoosedTo());
			message.setTo(to);
			message.setSendDate(new Date());
			messageService.save(message);
			return mapping.findForward(ActionResult.BACK);
		}
		List<User> users = userService.getUsersList("", "", "");
		emailForm.setUsers(users);
		emailForm.setAttachements(new LinkedList<Attachement>());
		return mapping.findForward(ActionResult.SUCCESS);
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

	/**
	 * Zwraca warto�� pola userService
	 * 
	 * @return Warto�� pola userService
	 */
	public UserBeanService getUserService() {
		return userService;
	}

	/**
	 * Ustawia warto�� pola userService
	 * 
	 * @param userService
	 *            Nowa warto�� pola userService
	 */
	public void setUserService(UserBeanService userService) {
		this.userService = userService;
	}
}
