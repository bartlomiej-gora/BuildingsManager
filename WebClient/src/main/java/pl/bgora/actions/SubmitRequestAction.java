/**
 * 
 */
package pl.bgora.actions;

import com.github.bgora.beans.session.bussiness.ProcessCreatorService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import pl.bgora.forms.AddRequestForm;
import pl.bgora.utils.ActionParams;
import pl.bgora.utils.ActionResult;
import pl.bgora.utils.ActionTools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Akcja zatwierdzenia procesu.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class SubmitRequestAction extends Action {

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
		AddRequestForm requestForm = (AddRequestForm) form;
		ProcessCreatorService procCreator = requestForm.getCreator();
		if (ActionTools.buttonHit(request, ActionParams.CANCEL)) {
			procCreator.cancel();
			return mapping.findForward(ActionResult.CANCEL);
		} else if (ActionTools.buttonHit(request, ActionParams.BACK)) {
			return mapping.findForward(ActionResult.BACK);
		} else if (ActionTools.buttonHit(request, ActionParams.APPLY)) {
			procCreator.startProccess(requestForm.getContent());
			return mapping.findForward(ActionResult.SUBMIT);
		}
		return mapping.findForward(ActionResult.SUCCESS);
	}
}
