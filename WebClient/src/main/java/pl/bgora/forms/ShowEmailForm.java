/**
 * 
 */
package pl.bgora.forms;

import com.github.bgora.beans.entity.Attachement;
import com.github.bgora.beans.entity.Message;
import com.github.bgora.beans.entity.User;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import java.util.List;

/**
 * Formatka podgl�du maila.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class ShowEmailForm extends ActionForm {

	private List<User> users;

	private String from;

	private String content;

	private List<Attachement> attachements;

	private FormFile attachement;

	private String title;

	private Message message;

	/**
	 * Zwraca warto�� pola users
	 * 
	 * @return Warto�� pola users
	 */
	public List<User> getUsers() {
		return users;
	}

	/**
	 * Zwraca warto�� pola choosedTo
	 * 
	 * @return Warto�� pola choosedTo
	 */
	public String getFrom() {
		return from;
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
	 * Zwraca warto�� pola attachements
	 * 
	 * @return Warto�� pola attachements
	 */
	public List<Attachement> getAttachements() {
		return attachements;
	}

	/**
	 * Ustawia warto�� pola users
	 * 
	 * @param users
	 *            Nowa warto�� pola users
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}

	/**
	 * Ustawia warto�� pola choosedTo
	 * 
	 * @param choosedTo
	 *            Nowa warto�� pola choosedTo
	 */
	public void setFrom(String from) {
		this.from = from;
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
	 * Ustawia warto�� pola attachements
	 * 
	 * @param attachements
	 *            Nowa warto�� pola attachements
	 */
	public void setAttachements(List<Attachement> attachements) {
		this.attachements = attachements;
	}

	/**
	 * Zwraca warto�� pola attachement
	 * 
	 * @return Warto�� pola attachement
	 */
	public FormFile getAttachement() {
		return attachement;
	}

	/**
	 * Ustawia warto�� pola attachement
	 * 
	 * @param attachement
	 *            Nowa warto�� pola attachement
	 */
	public void setAttachement(FormFile attachement) {
		this.attachement = attachement;
	}

	/**
	 * Zwraca warto�� pola title
	 * 
	 * @return Warto�� pola title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Ustawia warto�� pola title
	 * 
	 * @param title
	 *            Nowa warto�� pola title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Zwraca warto�� pola message
	 * 
	 * @return Warto�� pola message
	 */
	public Message getMessage() {
		return message;
	}

	/**
	 * Ustawia warto�� pola message
	 * 
	 * @param message
	 *            Nowa warto�� pola message
	 */
	public void setMessage(Message message) {
		this.message = message;
	}
}
