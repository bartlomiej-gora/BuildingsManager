/**
 * 
 */
package pl.bgora.actions;

import com.github.bgora.beans.entity.News;
import com.github.bgora.beans.session.bussiness.NewsBeanService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import pl.bgora.forms.WelcomeForm;
import pl.bgora.utils.ActionParams;
import pl.bgora.utils.ActionResult;
import pl.bgora.utils.ActionTools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Klasa akcji formatki powitalnej.
 * 
 * @author Bart�omiej G�ra
 * 
 */
public class WelcomeAction extends Action {

	private NewsBeanService newsBean;

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

		if (ActionTools.paramEquals(request, ActionParams.PAGE,
				ActionResult.CONTACT)) {
			return mapping.findForward(ActionResult.CONTACT);
		} else if (ActionTools.paramEquals(request, ActionParams.PAGE,
				ActionResult.ABOUT)) {
			return mapping.findForward(ActionResult.ABOUT);
		} else if (ActionTools.paramEquals(request, ActionParams.PAGE,
				ActionResult.DOCUMENTS)) {
			return mapping.findForward(ActionResult.DOCUMENTS);
		} else if (ActionTools.paramEquals(request, ActionParams.PAGE,
				ActionResult.MY_ACCOUNT)) {
			if (request.getSession().getAttribute(ActionParams.CURRENT_USER) != null) {
				return mapping.findForward(ActionResult.LOGGED_MY_ACCOUNT);
			}
			return mapping.findForward(ActionResult.MY_ACCOUNT);
		} else {
			List<News> news = newsBean.getRecentNews();
			WelcomeForm welcomeForm = (WelcomeForm) form; // NOSONAR
			welcomeForm.setRecentNews(news);
			return mapping.findForward(ActionResult.SUCCESS);
		}

	}

	/**
	 * Zwraca warto�� pola newsBean
	 * 
	 * @return Warto�� pola newsBean
	 */
	public final NewsBeanService getNewsBean() {
		return newsBean;
	}

	/**
	 * Ustawia warto�� pola newsBean
	 * 
	 * @param newsBean
	 *            Nowa warto�� pola newsBean
	 */
	public final void setNewsBean(NewsBeanService newsBean) {
		this.newsBean = newsBean; // NOSONAR
	}

}
