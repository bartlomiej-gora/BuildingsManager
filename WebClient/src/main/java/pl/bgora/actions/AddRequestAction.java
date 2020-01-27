/**
 * 
 */
package pl.bgora.actions;

import com.github.bgora.beans.entity.MainProcess;
import com.github.bgora.beans.entity.ProcessPathElement;
import com.github.bgora.beans.entity.User;
import com.github.bgora.beans.entity.constants.ProcessName;
import com.github.bgora.beans.session.bussiness.ProcessBeanService;
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
import java.util.List;

/**
 * Akcja sk��dania wniosk�w.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class AddRequestAction extends Action {

	private ProcessCreatorService creatorService;

	private ProcessBeanService proccessBean;

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
		User user = (User) request.getSession().getAttribute(
				ActionParams.CURRENT_USER);
		if (ActionTools.buttonHit(request, ActionParams.CANCEL)) {
			return mapping.findForward(ActionResult.BACK);
		} else if (ActionTools.buttonHit(request, ActionParams.SUBMIT)) {
			String processName = requestForm.getProcessName();
			ProcessName procName = ProcessName.get(processName);
			creatorService = requestForm.getCreator();
			creatorService.createProccess(procName, user);
			MainProcess proc = creatorService.getProccess();
			ProcessPathElement element = proc.getActualPathes().get(0);
			requestForm.setContent(element.getContent().getContent());
			return mapping.findForward(ActionResult.SUBMIT);
		} else {
			requestForm.setCreator(creatorService);
			List<MainProcess> processes = proccessBean.listProccessTemplates();
			requestForm.setProcesses(processes);
		}
		return mapping.findForward(ActionResult.SUCCESS);
	}

	/**
	 * Zwraca warto�� pola creatorService
	 * 
	 * @return Warto�� pola creatorService
	 */
	public ProcessCreatorService getCreatorService() {
		return creatorService;
	}

	/**
	 * Zwraca warto�� pola proccessBean
	 * 
	 * @return Warto�� pola proccessBean
	 */
	public ProcessBeanService getProccessBean() {
		return proccessBean;
	}

	/**
	 * Ustawia warto�� pola creatorService
	 * 
	 * @param creatorService
	 *            Nowa warto�� pola creatorService
	 */
	public void setCreatorService(ProcessCreatorService creatorService) {
		this.creatorService = creatorService;
	}

	/**
	 * Ustawia warto�� pola proccessBean
	 * 
	 * @param proccessBean
	 *            Nowa warto�� pola proccessBean
	 */
	public void setProccessBean(ProcessBeanService proccessBean) {
		this.proccessBean = proccessBean;
	}

}
