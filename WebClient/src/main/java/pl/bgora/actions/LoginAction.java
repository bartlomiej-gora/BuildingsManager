/**
 * 
 */
package pl.bgora.actions;

import com.github.bgora.beans.entity.User;
import com.github.bgora.beans.session.bussiness.UserBeanService;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import pl.bgora.forms.LoginForm;
import pl.bgora.utils.ActionParams;
import pl.bgora.utils.ActionResult;
import pl.bgora.utils.Constants;
import pl.bgora.utils.LoginUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.rmi.RemoteException;

/**
 * Akcja logowania do systemu i zmiany ustawie� zalogowanego u�ytkownika.
 * 
 * @author Bart�omiej G�ra
 * 
 */
public class LoginAction extends Action {

	private static Logger logger = Logger.getLogger(LoginAction.class);

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
		LoginForm loginForm = (LoginForm) form; // NOSONAR
		String hash = LoginUtils.getHashPassword(loginForm.getPassword());
		User user = userBean.login(loginForm.getLogin(), hash);
		if (user != null) {
			request.getSession().setAttribute(ActionParams.CURRENT_USER, user);
		} else {
			ActionErrors errors = new ActionErrors();
			errors.add("login", new ActionMessage(Constants.MSG_NO_SUCH_LOGIN,
					true));
			addErrors(request, errors);
			return mapping.findForward(ActionResult.FAILED);
		}
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
		this.userBean = userBean; // NOSONAR
	}

	public final void initData() {
		try {
			userBean.initData();
		} catch (RemoteException e) {
			logger.error(e.getMessage(), e);
		}
	}
}
