/**
 * 
 */
package pl.bgora.forms.decorator;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

import javax.servlet.jsp.PageContext;

/**
 * Klasa dekoratora dla kolumn zawieraj�cyh dane boolean.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class DisplayTagCheckBoxDecorator implements DisplaytagColumnDecorator {

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
		String value = (String) columnValue;
		if (value.equals("true")) {
			return "<input type=\"checkbox\" checked=\"checked\" disabled=\"disabled\"></input>";
		} else {
			return "<input type=\"checkbox\" disabled=\"disabled\"></input>";
		}
	}
}
