/**
 * 
 */
package pl.bgora.actions;

import com.github.bgora.beans.entity.User;
import com.github.bgora.beans.session.bussiness.UserBeanService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import pl.bgora.forms.UserAdminForm;
import pl.bgora.forms.decorator.UsersWrapper;
import pl.bgora.utils.ActionResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;

/**
 * Akcja wejscia na formatk� administracji.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class UserAdminAction extends Action {

	private UserBeanService userBean;

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public final ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception { // NOSONAR
		UserAdminForm adminForm = (UserAdminForm) form; // NOSONAR
		List<User> users = userBean.getUsersList("", "", "");
		List<UsersWrapper> wrappers = new LinkedList<UsersWrapper>();
		for (User user : users) {
			UsersWrapper wrapper = new UsersWrapper();
			wrapper.setUser(user);
			wrapper.setPerson(user.getPerson());
			wrapper.setRole(user.getRole());
			wrappers.add(wrapper);
		}
		adminForm.setUsersList(wrappers);
		return mapping.findForward(ActionResult.SUCCESS);
	}

	/**
	 * Zwraca warto�� pola userBean
	 * 
	 * @return Warto�� pola userBean
	 */
	public final UserBeanService getUserBean() {
		return userBean;
	}

	/**
	 * Ustawia warto�� pola userBean
	 * 
	 * @param userBean
	 *            Nowa warto�� pola userBean
	 */
	public final void setUserBean(UserBeanService userBean) {
		this.userBean = userBean;
	}
}
