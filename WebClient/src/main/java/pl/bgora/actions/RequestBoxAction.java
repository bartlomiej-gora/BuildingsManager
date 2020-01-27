/**
 * 
 */
package pl.bgora.actions;

import com.github.bgora.beans.entity.MainProcess;
import com.github.bgora.beans.entity.User;
import com.github.bgora.beans.session.bussiness.ProcessBeanService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import pl.bgora.forms.RequestBoxForm;
import pl.bgora.utils.ActionParams;
import pl.bgora.utils.ActionResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Akcja skrzynki z wnioskami.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class RequestBoxAction extends Action {

	private ProcessBeanService processBean;

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
		RequestBoxForm requestForm = (RequestBoxForm) form;
		User current = (User) request.getSession().getAttribute(
				ActionParams.CURRENT_USER);
		List<MainProcess> processes = processBean
				.listActualProcessesForUser(current);
		requestForm.setProcesses(processes);
		return mapping.findForward(ActionResult.SUCCESS);
	}

	/**
	 * Zwraca warto�� pola processBean
	 * 
	 * @return Warto�� pola processBean
	 */
	public ProcessBeanService getProcessBean() {
		return processBean;
	}

	/**
	 * Ustawia warto�� pola processBean
	 * 
	 * @param processBean
	 *            Nowa warto�� pola processBean
	 */
	public void setProcessBean(ProcessBeanService processBean) {
		this.processBean = processBean;
	}
}
