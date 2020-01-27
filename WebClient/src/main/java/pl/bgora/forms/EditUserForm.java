/**
 * 
 */
package pl.bgora.forms;

import com.github.bgora.beans.entity.BMSGroup;
import com.github.bgora.beans.entity.Role;
import com.github.bgora.beans.entity.constants.GroupNames;
import com.github.bgora.beans.entity.constants.RoleTypes;
import com.github.bgora.beans.session.bussiness.UserEditorService;
import org.apache.struts.action.ActionForm;

import java.util.List;

/**
 * Klasa formatki dla akcji dodawania u�ytkownik�w.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class EditUserForm extends ActionForm {

	private static final long serialVersionUID = -8595002250324850456L;

	// private User user;

	private String newPasswd;

	private String confirmPasswd;

	private List<Role> roles;

	private UserEditorService userEditor;

	private List<BMSGroup> groups;

	private BMSGroup group;

	/**
	 * Klasa wyliczenia okre�laj�cego tryby w jakich dzia�� formatka
	 * 
	 * @author Bart�omiej G�ra (Black007pl@gmail.com)
	 * 
	 */
	public enum UserFormMode {
		ADD, EDIT;
	}

	private UserFormMode mode;

	private String login;

	private Role role;

	/**
	 * Zwraca warto�� pola newPasswd
	 * 
	 * @return Warto�� pola newPasswd
	 */
	public String getNewPasswd() {
		return newPasswd;
	}

	/**
	 * Ustawia warto�� pola newPasswd
	 * 
	 * @param newPasswd
	 *            Nowa warto�� pola newPasswd
	 */
	public void setNewPasswd(String newPasswd) {
		this.newPasswd = newPasswd;
	}

	/**
	 * Zwraca warto�� pola confirmPasswd
	 * 
	 * @return Warto�� pola confirmPasswd
	 */
	public String getConfirmPasswd() {
		return confirmPasswd;
	}

	/**
	 * Ustawia warto�� pola confirmPasswd
	 * 
	 * @param confirmPasswd
	 *            Nowa warto�� pola confirmPasswd
	 */
	public void setConfirmPasswd(String confirmPasswd) {
		this.confirmPasswd = confirmPasswd;
	}

	/**
	 * Zwraca warto�� pola roles
	 * 
	 * @return Warto�� pola roles
	 */
	public List<Role> getRoles() {
		return roles;
	}

	/**
	 * Ustawia warto�� pola roles
	 * 
	 * @param roles
	 *            Nowa warto�� pola roles
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	/**
	 * Zwraca login u�ytkownika.
	 * 
	 * @return login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Ustawia login u�ytkownika
	 * 
	 * @param login
	 *            nazwa u�ytkownika.
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Ustawia rol� u�ytkownika.
	 * 
	 * @param role
	 *            Rola u�ytkownika.
	 * @see com.github.bgora.beans.entity.Role
	 */
	public void setRole(String role) {
		this.role = new Role();
		this.role.setRole(RoleTypes.valueOf(role));
		this.userEditor.setRole(this.role);
	}

	/**
	 * Zwraca rol� u�ytkwonika.
	 * 
	 * @return role Rola u�ytkownika.
	 * @see com.github.bgora.beans.entity.Role
	 */
	public String getRole() {
		return role != null ? role.toString() : "";
	}

	/**
	 * Zwraca warto�� pola mode
	 * 
	 * @return Warto�� pola mode
	 */
	public UserFormMode getMode() {
		return mode;
	}

	/**
	 * Ustawia warto�� pola mode
	 * 
	 * @param mode
	 *            Nowa warto�� pola mode
	 */
	public void setMode(UserFormMode mode) {
		this.mode = mode;
	}

	/**
	 * Zwraca warto�� pola userEditor
	 * 
	 * @return Warto�� pola userEditor
	 */
	public UserEditorService getUserEditor() {
		return userEditor;
	}

	/**
	 * Ustawia warto�� pola userEditor
	 * 
	 * @param userEditor
	 *            Nowa warto�� pola userEditor
	 */
	public void setUserEditor(UserEditorService userEditor) {
		this.userEditor = userEditor;
	}

	/**
	 * Zwraca imi� powi�zanej osoby
	 * 
	 * @return imi�
	 */
	public String getName() {
		return userEditor != null && userEditor.getPerson() != null ? userEditor
				.getPerson().getName()
				: "";
	}

	/**
	 * Zwraca nazwisko pozi�wanej osoby.
	 * 
	 * @return nazwisko.
	 */
	public String getSurname() {
		return userEditor != null && userEditor.getPerson() != null ? userEditor
				.getPerson().getSurname()
				: "";
	}

	/**
	 * Zwraca warto�� pola groups
	 * 
	 * @return Warto�� pola groups
	 */
	public List<BMSGroup> getGroups() {
		return groups;
	}

	/**
	 * Ustawia warto�� pola groups
	 * 
	 * @param groups
	 *            Nowa warto�� pola groups
	 */
	public void setGroups(List<BMSGroup> groups) {
		this.groups = groups;
	}

	/**
	 * Zwraca warto�� pola group
	 * 
	 * @return Warto�� pola group
	 */
	public String getGroup() {
		return group != null ? group.toString() : "";
	}

	/**
	 * Ustawia warto�� pola group
	 * 
	 * @param group
	 *            Nowa warto�� pola group
	 */
	public void setGroup(String group) {
		this.group = new BMSGroup();
		this.group.setGroupName(GroupNames.valueOf(group));
		this.userEditor.setGroup(this.group);
	}
}
