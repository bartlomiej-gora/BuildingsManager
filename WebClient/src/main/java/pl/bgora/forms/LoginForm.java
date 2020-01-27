/**
 * 
 */
package pl.bgora.forms;

import org.apache.struts.validator.ValidatorForm;

/**
 * Formatka Logowania i zmiany ustawie� zalogowanego u�ytkownika.
 * 
 * @author Bart�omiej G�ra
 * 
 */
public final class LoginForm extends ValidatorForm {

	private static final long serialVersionUID = -7893046584282815859L;

	private String login;

	private String password;

	/**
	 * Zwraca warto�� pola login
	 * 
	 * @return Warto�� pola login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Ustawia warto�� pola login
	 * 
	 * @param login
	 *            Nowa warto�� pola login
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Zwraca warto�� pola password
	 * 
	 * @return Warto�� pola password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Ustawia warto�� pola password
	 * 
	 * @param password
	 *            Nowa warto�� pola password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
