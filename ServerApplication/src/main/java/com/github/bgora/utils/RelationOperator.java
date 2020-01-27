/**
 * 
 */
package com.github.bgora.utils;

/**
 * Interfejs biznesowy dla Session beana zarz�dzaj�cego budynkami.
 * 
 * @author Bart�omiej G�ra
 * 
 */
public enum RelationOperator {
	EQUAL("="), GREATEROREQUALTHEN(">="), GREATERTHEN(">"), LESSOREQUALTHEN(
			"<="), LESSTHAN("<"), NONEQUAL("<>");

	private String operator;

	/**
	 * 
	 * Konstruktor. Tworzy instancj� obiektu
	 * 
	 * @param operator
	 *            Przypisany operator.
	 */
	RelationOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * Zwraca operator.
	 * 
	 * @return operator.
	 */
	public String getOperator() {
		return this.operator;
	}
}
