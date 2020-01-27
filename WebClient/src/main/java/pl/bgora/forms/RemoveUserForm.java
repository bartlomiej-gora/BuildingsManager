/**
 * 
 */
package pl.bgora.forms;

import com.github.bgora.beans.entity.User;
import org.apache.struts.action.ActionForm;

/**
 * Formatka dla usuawania u�ytkownika.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class RemoveUserForm extends ActionForm {

	private User user;

	/**
	 * Zwraca warto�� pola user
	 * 
	 * @return Warto�� pola user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Ustawia warto�� pola user
	 * 
	 * @param user
	 *            Nowa warto�� pola user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Zwraca login u�ytkownika do usuni�cia
	 * 
	 * @return
	 */
	public String getLogin() {
		return this.user.getLogin();
	}
}
