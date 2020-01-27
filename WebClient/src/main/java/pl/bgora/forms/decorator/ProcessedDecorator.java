/**
 * 
 */
package pl.bgora.forms.decorator;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

import javax.servlet.jsp.PageContext;

/**
 * Klasa dekoratora dla tabelki z wnioskami. B�dzie oznacza�a, kt�re wnioski s�
 * nowe i nale�y je sprocesowa�,a kt�re zosta�y ju� sprocesowane i mo�na je
 * tylko podejrze�.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class ProcessedDecorator implements DisplaytagColumnDecorator {

	@Override
	public Object decorate(Object columnValue, PageContext pageContext,
			MediaTypeEnum media) throws DecoratorException {
		Boolean column = (Boolean) columnValue;
		if (column.equals(Boolean.TRUE)) {
			return "<font color=\"blue\">zakończone</font>";
		} else {
			return "<font color=\"red\">!!!</font>";
		}
	}
}
