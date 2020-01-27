/**
 * 
 */
package pl.bgora.actions;

import com.github.bgora.beans.entity.User;
import com.github.bgora.beans.session.bussiness.UserBeanService;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import pl.bgora.forms.MyProfileForm;
import pl.bgora.utils.ActionParams;
import pl.bgora.utils.ActionResult;
import pl.bgora.utils.ActionTools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.rmi.RemoteException;

/**
 * Akcja dla edycji profilu u�ytkownika.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class MyProfileAction extends Action {

	private UserBeanService userBean;

	private static Logger logger = Logger.getLogger(MyProfileAction.class);

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

		if (ActionTools.buttonHit(request, ActionParams.SUBMIT)) {
			MyProfileForm profileForm = (MyProfileForm) form; // NOSONAR
			boolean isError = false;
			try {
				userBean.save(profileForm.getUser());
			} catch (RemoteException e) {
				logger.error(e);
			}
			if (!isError) {
				ActionMessage msg = new ActionMessage("profile.edit.success",
						true);
				ActionMessages messages = new ActionMessages();
				messages.add(ActionMessages.GLOBAL_MESSAGE, msg);
				addMessages(request, messages);
			}
			request.getSession().setAttribute(ActionParams.CURRENT_USER,
					profileForm.getUser());
		}
		User user = (User) request.getSession().getAttribute(
				ActionParams.CURRENT_USER);
		MyProfileForm profileForm = (MyProfileForm) form; // NOSONAR
		profileForm.setUser(user);
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
