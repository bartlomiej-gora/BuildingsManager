/**
 * 
 */
package com.github.bgora.beans.entity.constants;

/**
 * 
 * Nazwy proces�w.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public enum ProcessName {

	REQUEST_FOR_DISTRIBUTION_OF_PAYMENT(
			"Pro\u015Bba o roz\u0142o\u017Cenie p\u0142atno\u015Bci"), REQUEST_FOR_CHANGE_HEAT_METERS(
			"Wniosek o zmian\u0119 miernik\u00F3w ciep\u0142a");

	ProcessName(String displayName) {
		this.displayName = displayName;
	}

	private String displayName;

	public String getDisplayName() {
		return displayName;
	}

	public static ProcessName get(String displayName) {
		if (REQUEST_FOR_CHANGE_HEAT_METERS.getDisplayName().equals(displayName)) {
			return REQUEST_FOR_CHANGE_HEAT_METERS;
		} else if (REQUEST_FOR_DISTRIBUTION_OF_PAYMENT.getDisplayName().equals(
				displayName)) {
			return REQUEST_FOR_DISTRIBUTION_OF_PAYMENT;
		}
		return null;
	}

	@Override
	public String toString() {
		return getDisplayName();
	}
}
