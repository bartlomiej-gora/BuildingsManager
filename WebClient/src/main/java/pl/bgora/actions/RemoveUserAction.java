/**
 * 
 */
package pl.bgora.actions;

import com.github.bgora.beans.entity.User;
import com.github.bgora.beans.session.bussiness.UserBeanService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import pl.bgora.forms.RemoveUserForm;
import pl.bgora.utils.ActionParams;
import pl.bgora.utils.ActionResult;
import pl.bgora.utils.ActionTools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Akcja usuwania u�ytkownika.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class RemoveUserAction extends Action {

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
		if (ActionTools.buttonHit(request, ActionParams.SUBMIT)) {
			RemoveUserForm removeForm = (RemoveUserForm) form; // NOSONAR
			User u = removeForm.getUser();
			userService.remove(u);
			return mapping.findForward(ActionResult.BACK);
		}
		if (ActionTools.buttonHit(request, ActionParams.CANCEL)) {
			return mapping.findForward(ActionResult.BACK);
		}
		if (ActionTools.paramExists(request, ActionParams.ID)) {
			RemoveUserForm removeForm = (RemoveUserForm) form;
			String stringID = request.getParameter(ActionParams.ID);
			Long id = Long.valueOf(stringID);
			User u = userService.findById(id);
			removeForm.setUser(u);
		}
		return mapping.findForward(ActionResult.SUCCESS);
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
