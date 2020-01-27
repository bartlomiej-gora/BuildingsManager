/**
 *
 */
package pl.bgora.forms;

import com.github.bgora.beans.entity.News;
import org.apache.struts.action.ActionForm;

public class AddNewsForm extends ActionForm {

    private News news;

    public News getNews() {
        return news;
    }


    public void setNews(News news) {
        this.news = news;
    }

    public void setTitle(String title) {
        news.setTitle(title);
    }

    public String getTitle() {
        return news.getTitle();
    }

    public void setContent(String content) {
        news.setContent(content);
    }

    public String getContent() {
        return news.getContent();
    }
}
