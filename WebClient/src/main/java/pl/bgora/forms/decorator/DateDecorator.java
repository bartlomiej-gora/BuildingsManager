/**
 * 
 */
package pl.bgora.forms.decorator;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

import javax.servlet.jsp.PageContext;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Dekorator dla kolumn z dat�
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class DateDecorator implements DisplaytagColumnDecorator {

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see org.displaytag.decorator.DisplaytagColumnDecorator#decorate(java.lang.Object,
	 *      javax.servlet.jsp.PageContext,
	 *      org.displaytag.properties.MediaTypeEnum)
	 */
	@Override
	public Object decorate(Object columnValue, PageContext pageContext,
			MediaTypeEnum media) throws DecoratorException {
		Date date = (Date) columnValue;
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return fmt.format(date);
	}

}
