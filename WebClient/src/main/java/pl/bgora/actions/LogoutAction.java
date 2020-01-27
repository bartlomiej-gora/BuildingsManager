/**
 * 
 */
package pl.bgora.actions;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import pl.bgora.utils.ActionParams;
import pl.bgora.utils.ActionResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Klasa akcji wylogowania u�ytkownika.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class LogoutAction extends Action {

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public final ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception { // NOSONAR
		request.getSession().setAttribute(ActionParams.CURRENT_USER, null);
		return mapping.findForward(ActionResult.SUCCESS);
	}
}
