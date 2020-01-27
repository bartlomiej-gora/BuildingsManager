/**
 * 
 */
package pl.bgora.forms;

import com.github.bgora.beans.entity.News;
import org.apache.struts.action.ActionForm;

import java.util.List;

/**
 * Klasa formatki powitalnej.
 * 
 * @author Bart�omiej G�ra
 * 
 */
public final class WelcomeForm extends ActionForm {

	private static final long serialVersionUID = -5767685075494318031L;

	private List<News> recentNews;

	/**
	 * Zwraca warto�� pola recentNews
	 * 
	 * @return Warto�� pola recentNews
	 */
	public List<News> getRecentNews() {
		return recentNews;
	}

	/**
	 * Ustawia warto�� pola recentNews
	 * 
	 * @param recentNews
	 *            Nowa warto�� pola recentNews
	 */
	public void setRecentNews(List<News> recentNews) {
		this.recentNews = recentNews;
	}

}
