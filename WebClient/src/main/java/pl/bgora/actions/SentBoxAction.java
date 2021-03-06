/**
 * 
 */
package pl.bgora.actions;

import com.github.bgora.beans.entity.Message;
import com.github.bgora.beans.entity.User;
import com.github.bgora.beans.session.bussiness.MessageBeanService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import pl.bgora.forms.SentBoxForm;
import pl.bgora.utils.ActionParams;
import pl.bgora.utils.ActionResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Akcja Skrzynki z wys�anymi wiadomo�ciami.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class SentBoxAction extends Action {

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
			throws Exception {
		SentBoxForm sentForm = (SentBoxForm) form; // NOSONAR;
		User current = (User) request.getSession().getAttribute(
				ActionParams.CURRENT_USER);
		List<Message> messages = messageService.listSentMessages(current);
		sentForm.setMessages(messages);
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
}
