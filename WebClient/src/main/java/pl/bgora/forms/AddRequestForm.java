/**
 * 
 */
package pl.bgora.forms;

import com.github.bgora.beans.entity.MainProcess;
import com.github.bgora.beans.session.bussiness.ProcessCreatorService;
import org.apache.struts.action.ActionForm;

import java.util.List;

/**
 * Formatka do sk��dania wniosk�w.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class AddRequestForm extends ActionForm {

	private String processName;

	private List<MainProcess> processes;

	private String content;

	private ProcessCreatorService creator;

	/**
	 * Zwraca warto�� pola processName
	 * 
	 * @return Warto�� pola processName
	 */
	public String getProcessName() {
		return processName;
	}

	/**
	 * Zwraca warto�� pola processes
	 * 
	 * @return Warto�� pola processes
	 */
	public List<MainProcess> getProcesses() {
		return processes;
	}

	/**
	 * Ustawia warto�� pola processName
	 * 
	 * @param processName
	 *            Nowa warto�� pola processName
	 */
	public void setProcessName(String processName) {
		this.processName = processName;
	}

	/**
	 * Ustawia warto�� pola processes
	 * 
	 * @param processes
	 *            Nowa warto�� pola processes
	 */
	public void setProcesses(List<MainProcess> processes) {
		this.processes = processes;
	}

	/**
	 * Zwraca warto�� pola content
	 * 
	 * @return Warto�� pola content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Ustawia warto�� pola content
	 * 
	 * @param content
	 *            Nowa warto�� pola content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Zwraca warto�� pola creator
	 * 
	 * @return Warto�� pola creator
	 */
	public ProcessCreatorService getCreator() {
		return creator;
	}

	/**
	 * Ustawia warto�� pola creator
	 * 
	 * @param creator
	 *            Nowa warto�� pola creator
	 */
	public void setCreator(ProcessCreatorService creator) {
		this.creator = creator;
	}
}
