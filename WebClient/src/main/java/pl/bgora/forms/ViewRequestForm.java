/**
 * 
 */
package pl.bgora.forms;

import com.github.bgora.beans.entity.MainProcess;
import com.github.bgora.beans.entity.ProcessPathElement;
import org.apache.struts.action.ActionForm;

import java.util.List;

/**
 * Formatka podgl�du wniosku.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class ViewRequestForm extends ActionForm {

	private List<ProcessPathElement> history;

	private ProcessPathElement currentElement;

	private MainProcess proccess;

	/**
	 * Zwraca warto�� pola history
	 * 
	 * @return Warto�� pola history
	 */
	public List<ProcessPathElement> getHistory() {
		return history;
	}

	/**
	 * Zwraca warto�� pola currentElement
	 * 
	 * @return Warto�� pola currentElement
	 */
	public ProcessPathElement getCurrentElement() {
		return currentElement;
	}

	/**
	 * Zwraca warto�� pola proccess
	 * 
	 * @return Warto�� pola proccess
	 */
	public MainProcess getProccess() {
		return proccess;
	}

	/**
	 * Ustawia warto�� pola history
	 * 
	 * @param history
	 *            Nowa warto�� pola history
	 */
	public void setHistory(List<ProcessPathElement> history) {
		this.history = history;
	}

	/**
	 * Ustawia warto�� pola currentElement
	 * 
	 * @param currentElement
	 *            Nowa warto�� pola currentElement
	 */
	public void setCurrentElement(ProcessPathElement currentElement) {
		this.currentElement = currentElement;
	}

	/**
	 * Ustawia warto�� pola proccess
	 * 
	 * @param proccess
	 *            Nowa warto�� pola proccess
	 */
	public void setProccess(MainProcess proccess) {
		this.proccess = proccess;
	}

	public String getProccessName() {
		return proccess.getDisplayName();
	}

	public String getAuthor() {
		return currentElement.getContent().getAuthor().getLogin();
	}

	public String getStatus() {
		return proccess.getStatus().getDisplay();
	}

	public String getPlacement() {
		return proccess.getPlacement();
	}

	public void setContent(String content) {
		currentElement.getContent().setContent(content);
	}

	public String getContent() {
		return currentElement.getContent().getContent();
	}

	public void setComment(String comment) {
		this.currentElement.setUserComment(comment);
	}

	public String getComment() {
		return currentElement.getUserComment();
	}
}
