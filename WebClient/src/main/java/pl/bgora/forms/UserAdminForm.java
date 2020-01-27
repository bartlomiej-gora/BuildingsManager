/**
 * 
 */
package pl.bgora.forms;

import org.apache.struts.action.ActionForm;
import pl.bgora.forms.decorator.UsersWrapper;

import java.util.List;

/**
 * Formatka administracji systemem.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public final class UserAdminForm extends ActionForm {

	private static final long serialVersionUID = -793598966633564989L;

	private List<UsersWrapper> usersList;

	/**
	 * Zwraca warto�� pola usersList
	 * 
	 * @return Warto�� pola usersList
	 */
	public List<UsersWrapper> getUsersList() {
		return usersList;
	}

	/**
	 * Ustawia warto�� pola usersList
	 * 
	 * @param usersList
	 *            Nowa warto�� pola usersList
	 */
	public void setUsersList(List<UsersWrapper> usersList) {
		this.usersList = usersList;
	}

}
