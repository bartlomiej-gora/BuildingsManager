/**
 * 
 */
package pl.bgora.actions;

import com.github.bgora.beans.entity.MainProcess;
import com.github.bgora.beans.entity.ProcessPathElement;
import com.github.bgora.beans.session.bussiness.ProcessBeanService;
import com.github.bgora.beans.session.bussiness.ProposalDataBeanService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import pl.bgora.forms.ViewRequestForm;
import pl.bgora.utils.ActionParams;
import pl.bgora.utils.ActionResult;
import pl.bgora.utils.ActionTools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;

/**
 * Akcja podgl�du wniosku.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class ViewRequestAction extends Action {

	private ProposalDataBeanService proposalDataBean;
	private ProcessBeanService processService;

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
		ViewRequestForm requestForm = (ViewRequestForm) form;
		if (ActionTools.paramExists(request, ActionParams.ID)) {
			Long id = Long.valueOf(request.getParameter(ActionParams.ID));
			MainProcess proccess = processService.getById(id);
			ProcessPathElement currentElement = proccess.getActualPathes().get(
					proccess.getActualPathes().size() - 1);
			List<ProcessPathElement> history = new LinkedList<ProcessPathElement>(
					proccess.getActualPathes());
			history.remove(0);
			history.remove(history.size() - 1);
			requestForm.setHistory(history);
			requestForm.setCurrentElement(currentElement);
			requestForm.setProccess(proccess);
		} else if (ActionTools.buttonHit(request, ActionParams.REFUSE)) {
			MainProcess proccess = requestForm.getProccess();
			processService.refuseProccess(proccess);
			return mapping.findForward(ActionResult.BACK);

		} else if (ActionTools.buttonHit(request, ActionParams.RETURN)) {
			MainProcess proccess = requestForm.getProccess();
			processService.returnProccess(proccess);
			return mapping.findForward(ActionResult.BACK);
		} else if (ActionTools.buttonHit(request, ActionParams.ACCEPT)) {
			MainProcess proccess = requestForm.getProccess();
			processService.acceptProccess(proccess);
			return mapping.findForward(ActionResult.BACK);
		} else if (ActionTools.buttonHit(request, ActionParams.BACK)) {
			return mapping.findForward(ActionResult.BACK);
		}
		return mapping.findForward(ActionResult.SUCCESS);
	}

	/**
	 * Zwraca warto�� pola proposalDataBean
	 * 
	 * @return Warto�� pola proposalDataBean
	 */
	public ProposalDataBeanService getProposalDataBean() {
		return proposalDataBean;
	}

	/**
	 * Ustawia warto�� pola proposalDataBean
	 * 
	 * @param proposalDataBean
	 *            Nowa warto�� pola proposalDataBean
	 */
	public void setProposalDataBean(ProposalDataBeanService proposalDataBean) {
		this.proposalDataBean = proposalDataBean;
	}

	/**
	 * Zwraca warto�� pola processService
	 * 
	 * @return Warto�� pola processService
	 */
	public ProcessBeanService getProcessService() {
		return processService;
	}

	/**
	 * Ustawia warto�� pola processService
	 * 
	 * @param processService
	 *            Nowa warto�� pola processService
	 */
	public void setProcessService(ProcessBeanService processService) {
		this.processService = processService;
	}
}
