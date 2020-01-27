/**
 * 
 */
package pl.bgora.forms;

import com.github.bgora.beans.entity.Message;
import org.apache.struts.action.ActionForm;

import java.util.List;

/**
 * Formatka skrzynki odbiorczej
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class InboxForm extends ActionForm {

	private List<Message> messages;

	/**
	 * Zwraca warto�� pola messages
	 * 
	 * @return Warto�� pola messages
	 */
	public List<Message> getMessages() {
		return messages;
	}

	/**
	 * Ustawia warto�� pola messages
	 * 
	 * @param messages
	 *            Nowa warto�� pola messages
	 */
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
}
