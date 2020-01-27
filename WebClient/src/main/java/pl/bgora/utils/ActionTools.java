/**
 * 
 */
package pl.bgora.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Kolasa pomocnicza dla akcji struts.
 * 
 * @author Bart�omiej G�ra
 * 
 */
public final class ActionTools {

	private ActionTools() {
	}

	/**
	 * Sprawdza, czy podany parametr w requescie jest r�wny podanej warto�ci.
	 * 
	 * @param request
	 *            Obiekt ��dania http
	 * @param param
	 *            nazwa parametru
	 * @param value
	 *            oczekiwana warto��
	 * @return <code>true</code> je�li parametr o podanej nazwie zawiera zadan�
	 *         warto��, <code>false</code> w przeciwnym wypadku
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
	 * Metoda pomocnicza do sprawdzania, czy zosta� przyci�ni�ty przycisk.
	 * 
	 * @param request
	 *            Obiekt ��dania
	 * @param button
	 *            przycisk w postaci String
	 * @return <code>true</code> je�li parametr o podanej nazwie itsnieje
	 *         warto��, <code>false</code> w przeciwnym wypadku
	 */
	public static boolean buttonHit(HttpServletRequest request, String button) {
		if (request.getParameter(button) != null) {
			return true;
		}
		return false;
	}

	/**
	 * Metoda pomocnicza wprowadzana dla przejzysto�ci kodu.
	 * 
	 * @param request
	 *            Obiekt ��dania
	 * @param button
	 *            przycisk w postaci String
	 * @return <code>true</code> je�li parametr o podanej nazwie itsnieje
	 *         warto��, <code>false</code> w przeciwnym wypadku
	 */
	public static boolean paramExists(HttpServletRequest request,
			String paramName) {
		/*
		 * Poniewa� metoda buttonHit i metoda paramExists robi� to samo, a
		 * metoda buttonHit zosta�a zaimplementowana wcze�niej, to korzystamy z
		 * tego. Metoda paramExists jest potrzebna dla zachowania przejrzysto�ci
		 * kodu
		 */
		return buttonHit(request, paramName);
	}
}
