/**
 * 
 */
package pl.bgora.forms;

import org.apache.struts.validator.ValidatorForm;

/**
 * Formatka Logowania i zmiany ustawieñ zalogowanego u¿ytkownika.
 * 
 * @author Bart³omiej Góra
 * 
 */
public final class LoginForm extends ValidatorForm {

	private static final long serialVersionUID = -7893046584282815859L;

	private String login;

	private String password;

	/**
	 * Zwraca wartoœæ pola login
	 * 
	 * @return Wartoœæ pola login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Ustawia wartoœæ pola login
	 * 
	 * @param login
	 *            Nowa wartoœæ pola login
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Zwraca wartoœæ pola password
	 * 
	 * @return Wartoœæ pola password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Ustawia wartoœæ pola password
	 * 
	 * @param password
	 *            Nowa wartoœæ pola password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
