/**
 * 
 */
package pl.bgora.actions;

import com.github.bgora.beans.entity.BMSGroup;
import com.github.bgora.beans.entity.Person;
import com.github.bgora.beans.entity.Role;
import com.github.bgora.beans.entity.User;
import com.github.bgora.beans.entity.constants.RoleTypes;
import com.github.bgora.beans.session.bussiness.GroupBeanService;
import com.github.bgora.beans.session.bussiness.PersonBeanService;
import com.github.bgora.beans.session.bussiness.RoleBeanService;
import com.github.bgora.beans.session.bussiness.UserBeanService;
import com.github.bgora.beans.session.bussiness.UserEditorService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import pl.bgora.forms.EditUserForm;
import pl.bgora.forms.EditUserForm.UserFormMode;
import pl.bgora.utils.ActionParams;
import pl.bgora.utils.ActionResult;
import pl.bgora.utils.ActionTools;
import pl.bgora.utils.Constants;
import pl.bgora.utils.LoginUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Klasa akcji dla operacji dodawania u�ytkownik�w.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public final class EditUserAction extends Action {

	private UserBeanService userBean;

	private RoleBeanService roleBean;

	private PersonBeanService personBean;

	private UserEditorService userEditor;

	private GroupBeanService groupBean;

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception { // NOSONAR
		if (ActionTools.paramEquals(request, ActionParams.NEW_USER, "true")) {
			EditUserForm editForm = (EditUserForm) form; // NOSONAR
			User usr = new User();
			Person p = new Person();
			Role role = new Role();
			BMSGroup group = new BMSGroup();
			role.setRole(RoleTypes.CLIENT);
			userEditor.setUser(usr);
			userEditor.setPerson(p);
			userEditor.setRole(role);
			userEditor.setGroup(group);
			editForm.setUserEditor(userEditor);
			List<BMSGroup> groups = groupBean.getGroups();
			List<Role> roles = roleBean.getRoles();
			editForm.setRoles(roles);
			editForm.setGroups(groups);
			editForm.setMode(UserFormMode.ADD);
			editForm.setNewPasswd("");
			editForm.setConfirmPasswd("");

		} else if (ActionTools.paramExists(request, ActionParams.ID)) {
			String tempVar = request.getParameter(ActionParams.ID);
			Long id = Long.valueOf(tempVar);
			User user = userBean.getUserById(id);
			EditUserForm editForm = (EditUserForm) form; // NOSONAR
			userEditor.setUser(user);
			editForm.setUserEditor(userEditor);
			List<BMSGroup> groups = groupBean.getGroups();
			editForm.setGroups(groups);
			List<Role> roles = roleBean.getRoles();
			editForm.setRoles(roles);
			editForm.setMode(UserFormMode.EDIT);
			editForm.setNewPasswd("");
			editForm.setConfirmPasswd("");
			editForm.setLogin(user.getLogin());
			editForm.setRole(user.getRole().getRole().name());
			editForm.setGroup(user.getGroups().get(0).getGroupName().name());
			// zapis.
		} else if (ActionTools.buttonHit(request, ActionParams.APPLY)) {
			EditUserForm editForm = (EditUserForm) form; // NOSONAR
			/*
			 * Sprawd�, czy obydwa has�a s� r�wne.
			 */
			String newPasswd = editForm.getNewPasswd();
			String confirmedPasswd = editForm.getConfirmPasswd();
			String login = editForm.getLogin();
			boolean isError = false;
			ActionMessages msg = new ActionMessages();
			if (login == null || login.equals("")) {
				msg.add("login", new ActionMessage(Constants.NO_LOGIN, true));
				isError = true;
			}
			if (newPasswd == null || newPasswd.equals("")) {
				msg.add("newPaswd", new ActionMessage(Constants.NO_NEW_PASSWD,
						true));
				isError = true;
			}
			if (confirmedPasswd == null || confirmedPasswd.equals("")) {
				msg.add("confirmedPasswd", new ActionMessage(
						Constants.NO_CONFIRMED_PASSWD, true));
				isError = true;
			}
			if ((newPasswd != null) && (confirmedPasswd != null)
					&& (!newPasswd.equals(confirmedPasswd))) {
				msg.add("newPasswd", new ActionMessage(
						Constants.PASSWD_NOT_EQUALS, true));
				isError = true;
			}
			if (isError) {
				// addMessages(request, msg);
				saveMessages(request, msg);
				return mapping.findForward(ActionResult.FAILED);
			}
			/*
			 * Koniec walidacji. Je�li nie by�o return, to jedziemy dalej i
			 * zapisujemy dane jak trzeba.
			 */
			String roleName = editForm.getRole();
			String groupName = editForm.getGroup();
			BMSGroup group = groupBean.findByName(groupName);
			Role role = roleBean.findRoleByName(roleName);
			userEditor = editForm.getUserEditor();
			User user = userEditor.getUser();
			user.setLogin(editForm.getLogin());
			user.setPasswd(LoginUtils.getHashPassword(editForm.getNewPasswd()));
			userEditor.setUser(user);
			userEditor.setRole(role);
			userEditor.setGroup(group);
			userEditor.commit();
			editForm.setLogin("");
			return mapping.findForward(ActionResult.BACK);
		} else if (ActionTools.buttonHit(request, ActionParams.CANCEL)) {
			EditUserForm editForm = (EditUserForm) form; // NOSONAR
			userEditor = editForm.getUserEditor();
			userEditor.cancel();
			editForm.setLogin("");
			return mapping.findForward(ActionResult.BACK);
		} else if (ActionTools.buttonHit(request, ActionParams.FIND_PERSON)) {
			EditUserForm editForm = (EditUserForm) form; // NOSONAR
			editForm.setUserEditor(userEditor);
			return mapping.findForward(ActionResult.SHOW_PERSONS);
		} else if (ActionTools.paramExists(request, ActionParams.PERSON_ID)) {
			String sId = request.getParameter(ActionParams.PERSON_ID);
			Long id = Long.valueOf(sId);
			Person p = personBean.findById(id);
			EditUserForm editForm = (EditUserForm) form; // NOSONAR
			userEditor = editForm.getUserEditor();
			userEditor.setPerson(p);
			editForm.setUserEditor(userEditor);
		}
		return mapping.findForward(ActionResult.SUCCESS);
	}

	/**
	 * Zwraca warto�� pola userBean
	 * 
	 * @return Warto�� pola userBean
	 */
	public UserBeanService getUserBean() {
		return userBean;
	}

	/**
	 * Ustawia warto�� pola userBean
	 * 
	 * @param userBean
	 *            Nowa warto�� pola userBean
	 */
	public void setUserBean(UserBeanService userBean) {
		this.userBean = userBean;
	}

	/**
	 * Zwraca warto�� pola roleBean
	 * 
	 * @return Warto�� pola roleBean
	 */
	public RoleBeanService getRoleBean() {
		return roleBean;
	}

	/**
	 * Ustawia warto�� pola roleBean
	 * 
	 * @param roleBean
	 *            Nowa warto�� pola roleBean
	 */
	public void setRoleBean(RoleBeanService roleBean) {
		this.roleBean = roleBean;
	}

	/**
	 * Zwraca warto�� pola personBean
	 * 
	 * @return Warto�� pola personBean
	 */
	public PersonBeanService getPersonBean() {
		return personBean;
	}

	/**
	 * Ustawia warto�� pola personBean
	 * 
	 * @param personBean
	 *            Nowa warto�� pola personBean
	 */
	public void setPersonBean(PersonBeanService personBean) {
		this.personBean = personBean;
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
	 * Zwraca warto�� pola groupBean
	 * 
	 * @return Warto�� pola groupBean
	 */
	public GroupBeanService getGroupBean() {
		return groupBean;
	}

	/**
	 * Ustawia warto�� pola groupBean
	 * 
	 * @param groupBean
	 *            Nowa warto�� pola groupBean
	 */
	public void setGroupBean(GroupBeanService groupBean) {
		this.groupBean = groupBean;
	}
}
