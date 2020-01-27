/**
 * 
 */
package pl.bgora.forms;

import com.github.bgora.beans.entity.User;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import pl.bgora.utils.ActionParams;
import pl.bgora.utils.ActionTools;
import pl.bgora.utils.LoginUtils;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;

/**
 * Klasa formatki dla edycji profilu u�ytkownika
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class MyProfileForm extends ActionForm {

	private static final long serialVersionUID = -6440535275388958784L;

	private User user;

	private String oldPasswd;

	private String newPasswd;

	private String confirmPasswd;

	private final Logger logger = Logger.getLogger(MyProfileForm.class);

	/**
	 * Zwraca warto�� pola user
	 * 
	 * @return Warto�� pola user
	 */
	public final User getUser() {
		return user;
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see org.apache.struts.action.ActionForm#validate(org.apache.struts.action.ActionMapping,
	 *      javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public final ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		String hash = null;
		if (ActionTools.buttonHit(request, ActionParams.SUBMIT)) {
			try {
				hash = LoginUtils.getHashPassword(oldPasswd);
			} catch (NoSuchAlgorithmException e) {
				logger.error(e.getMessage(), e);
			}
			if (!user.getPasswd().equals(hash)) {
				errors.add("oldPasswd", new ActionMessage(
						"error.wrong.oldPasswd"));
			}
			if (newPasswd == null) {
				errors.add("newPasswd", new ActionMessage("errors.required",
						"page.myaccount.newpasswd"));
			}
			if (confirmPasswd == null) {
				errors.add("oldPasswd", new ActionMessage("errors.required",
						"page.myaccount.confirmpasswd"));
			}
			if (((newPasswd != null) && (!newPasswd.equals(confirmPasswd)))
					|| ((confirmPasswd != null) && (!confirmPasswd
							.equals(newPasswd)))) {
				errors.add("oldPasswd", new ActionMessage(
						"error.wrong.samePasswd"));
			}

			if (errors.size() == 0) {
				try {
					user.setPasswd(LoginUtils.getHashPassword(newPasswd));
				} catch (NoSuchAlgorithmException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
		return errors;
	}

	/**
	 * Ustawia warto�� pola user
	 * 
	 * @param user
	 *            Nowa warto�� pola user
	 */
	public final void setUser(User user) {
		this.user = user;
	}

	/**
	 * Zwraca warto�� pola oldPasswd
	 * 
	 * @return Warto�� pola oldPasswd
	 */
	public final String getOldPasswd() {
		return oldPasswd;
	}

	/**
	 * Ustawia warto�� pola oldPasswd
	 * 
	 * @param oldPasswd
	 *            Nowa warto�� pola oldPasswd
	 */
	public final void setOldPasswd(String oldPasswd) {
		this.oldPasswd = oldPasswd;
	}

	/**
	 * Zwraca warto�� pola newPasswd
	 * 
	 * @return Warto�� pola newPasswd
	 */
	public final String getNewPasswd() {
		return newPasswd;
	}

	/**
	 * Ustawia warto�� pola newPasswd
	 * 
	 * @param newPasswd
	 *            Nowa warto�� pola newPasswd
	 */
	public final void setNewPasswd(String newPasswd) {
		this.newPasswd = newPasswd;
	}

	/**
	 * Zwraca warto�� pola confirmPasswd
	 * 
	 * @return Warto�� pola confirmPasswd
	 */
	public final String getConfirmPasswd() {
		return confirmPasswd;
	}

	/**
	 * Ustawia warto�� pola confirmPasswd
	 * 
	 * @param confirmPasswd
	 *            Nowa warto�� pola confirmPasswd
	 */
	public final void setConfirmPasswd(String confirmPasswd) {
		this.confirmPasswd = confirmPasswd;
	}

	/**
	 * Zwraca login u�ytkownika
	 * 
	 * @return login
	 */
	public final String getLogin() {
		return user.getLogin();
	}

}
