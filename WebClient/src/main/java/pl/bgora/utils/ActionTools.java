/**
 * 
 */
package pl.bgora.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Kolasa pomocnicza dla akcji struts.
 * 
 * @author Bart³omiej Góra
 * 
 */
public final class ActionTools {

	private ActionTools() {
	}

	/**
	 * Sprawdza, czy podany parametr w requescie jest równy podanej wartoœci.
	 * 
	 * @param request
	 *            Obiekt ¿¹dania http
	 * @param param
	 *            nazwa parametru
	 * @param value
	 *            oczekiwana wartoœæ
	 * @return <code>true</code> jeœli parametr o podanej nazwie zawiera zadan¹
	 *         wartoœæ, <code>false</code> w przeciwnym wypadku
	 */
	public static boolean paramEquals(HttpServletRequest request, String param,
			String value) {
		if (request.getParameter(param) != null
				&& request.getParameter(param).equals(value)) {
			return true;
		}
		return false;
	}

	/**
	 * Metoda pomocnicza do sprawdzania, czy zosta³ przyciœniêty przycisk.
	 * 
	 * @param request
	 *            Obiekt ¿¹dania
	 * @param button
	 *            przycisk w postaci String
	 * @return <code>true</code> jeœli parametr o podanej nazwie itsnieje
	 *         wartoœæ, <code>false</code> w przeciwnym wypadku
	 */
	public static boolean buttonHit(HttpServletRequest request, String button) {
		if (request.getParameter(button) != null) {
			return true;
		}
		return false;
	}

	/**
	 * Metoda pomocnicza wprowadzana dla przejzystoœci kodu.
	 * 
	 * @param request
	 *            Obiekt ¿¹dania
	 * @param button
	 *            przycisk w postaci String
	 * @return <code>true</code> jeœli parametr o podanej nazwie itsnieje
	 *         wartoœæ, <code>false</code> w przeciwnym wypadku
	 */
	public static boolean paramExists(HttpServletRequest request,
			String paramName) {
		/*
		 * Poniewa¿ metoda buttonHit i metoda paramExists robi¹ to samo, a
		 * metoda buttonHit zosta³a zaimplementowana wczeœniej, to korzystamy z
		 * tego. Metoda paramExists jest potrzebna dla zachowania przejrzystoœci
		 * kodu
		 */
		return buttonHit(request, paramName);
	}
}
