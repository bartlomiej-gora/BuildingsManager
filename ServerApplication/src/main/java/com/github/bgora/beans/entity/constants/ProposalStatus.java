/**
 * 
 */
package com.github.bgora.beans.entity.constants;

/**
 * Enum okreslaj�cy stany w jakich znajduje si� wniosek.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public enum ProposalStatus {

	APPLIED("Z\u0142o\u017Cony"), ACCEPTED("Zaakceptowany"), REFUSED(
			"Odrzucony"), PROCESSING("Przetwarzany"), REALIZED("Zrealizowany"), COMPLETED(
			"Zako\u0144czony");

	private String displayStatus;

	private ProposalStatus(String status) {
		displayStatus = status;
	}

	public static ProposalStatus get(String status) {
		if (ACCEPTED.getDisplay().equals(status)) {
			return ACCEPTED;
		} else if (APPLIED.getDisplay().equals(status)) {
			return APPLIED;
		} else if (REFUSED.getDisplay().equals(status)) {
			return REFUSED;
		} else if (PROCESSING.getDisplay().equals(status)) {
			return PROCESSING;
		} else if (REALIZED.getDisplay().equals(status)) {
			return REALIZED;
		} else if (COMPLETED.getDisplay().equals(status)) {
			return COMPLETED;
		}
		return null;
	}

	public String getDisplay() {
		return displayStatus;
	}

	@Override
	public String toString() {
		return getDisplay();
	}
}
