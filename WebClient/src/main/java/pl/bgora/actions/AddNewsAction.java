/**
 * 
 */
package pl.bgora.actions;

import com.github.bgora.beans.entity.News;
import com.github.bgora.beans.entity.User;
import com.github.bgora.beans.session.bussiness.NewsBeanService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import pl.bgora.forms.AddNewsForm;
import pl.bgora.utils.ActionParams;
import pl.bgora.utils.ActionResult;
import pl.bgora.utils.ActionTools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * akcja dodawania wiadomo��i.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class AddNewsAction extends Action {

	private NewsBeanService newsService;

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
			throws Exception {
		if (ActionTools.buttonHit(request, ActionParams.SUBMIT)) {
			AddNewsForm addForm = (AddNewsForm) form;
			News news = addForm.getNews();
			news.setNewsDate(new Date());
			User user = (User) request.getSession().getAttribute(
					ActionParams.CURRENT_USER);
			news.setUser(user);
			newsService.save(news);
			return mapping.findForward(ActionResult.BACK);
		} else if (ActionTools.buttonHit(request, ActionParams.CANCEL)) {
			return mapping.findForward(ActionResult.BACK);
		}
		AddNewsForm addForm = (AddNewsForm) form;
		addForm.setNews(new News());
		return mapping.findForward(ActionResult.SUCCESS);
	}

	/**
	 * Zwraca warto�� pola newsService
	 * 
	 * @return Warto�� pola newsService
	 */
	public NewsBeanService getNewsService() {
		return newsService;
	}

	/**
	 * Ustawia warto�� pola newsService
	 * 
	 * @param newsService
	 *            Nowa warto�� pola newsService
	 */
	public void setNewsService(NewsBeanService newsService) {
		this.newsService = newsService;
	}
}
