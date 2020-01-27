/**
 * 
 */
package pl.bgora.utils;

/**
 * Interfejs pomoczniczy ze sta�ymi kodami powr�tu z akcji.
 * 
 * @author Bart�omiej G�ra
 * 
 */
public interface ActionResult {

	/**
	 * Okre�la poprawne wykonanie akcji.
	 */
	String SUCCESS = "success";
	/**
	 * Okre�la akcje przejscia do strony kontaktu.
	 */
	String CONTACT = "contact";
	/**
	 * Okre�la akcje przejscia do strony o programie.
	 */
	String ABOUT = "about";
	/**
	 * Okre�la akcj� przej�cia do strony z dokumentami.
	 */
	String DOCUMENTS = "documents";
	/**
	 * Okre�la akcj� przej�cia do strony logowania.
	 */
	String MY_ACCOUNT = "myaccount";

	/**
	 * Okre�la wynik akcji do przej�cia do zak�adki my account dla zalogowanego
	 * u�ytkownika.
	 */
	String LOGGED_MY_ACCOUNT = "myaccount_logged";
	/**
	 * Czy operacja si� uda�a?
	 */
	String FAILED = "failed";
	/**
	 * Okre�la wynik dla akcji wy�wietlenia mieszka� dla wybranego budynku.
	 */
	String SHOW_FLATS = "show_flats";

	/**
	 * Id� do administracji budynkami.
	 */
	String GO_BUILDING_ADMIN = "buildingAdmin";

	/**
	 * id� spowrotem.
	 */
	String BACK = "back";
	/**
	 * Sta�a okre�laj�ca akcj� wy�wietlenia listy os�b dla potrzeb skojarzenia
	 * ich z u�ytkownikami.
	 */
	String SHOW_PERSONS = "show_persons";
	/**
	 * Sta�� okre�laj�ca adres struts z list� budynk�w.
	 */
	String FIND_ADDRESS = "find_address";
	/**
	 * Wybrano proces do uruchomienia.
	 */
	String SUBMIT = "submit";

	String CANCEL = "cancel";

}
