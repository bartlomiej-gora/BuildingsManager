/**
 * 
 */
package pl.bgora.forms;

import com.github.bgora.beans.entity.MainProcess;
import org.apache.struts.action.ActionForm;

import java.util.List;

/**
 * Formatka dla skrzynki wniosk�w.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class RequestBoxForm extends ActionForm {

	private List<MainProcess> processes;

	/**
	 * Zwraca warto�� pola processes
	 * 
	 * @return Warto�� pola processes
	 */
	public List<MainProcess> getProcesses() {
		return processes;
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
}
