/**
 * 
 */
package pl.bgora.utils;

/**
 * Interfejs pomoczniczy ze sta³ymi kodami powrótu z akcji.
 * 
 * @author Bart³omiej Góra
 * 
 */
public interface ActionResult {

	/**
	 * Okreœla poprawne wykonanie akcji.
	 */
	String SUCCESS = "success";
	/**
	 * Okreœla akcje przejscia do strony kontaktu.
	 */
	String CONTACT = "contact";
	/**
	 * Okreœla akcje przejscia do strony o programie.
	 */
	String ABOUT = "about";
	/**
	 * Okreœla akcjê przejœcia do strony z dokumentami.
	 */
	String DOCUMENTS = "documents";
	/**
	 * Okreœla akcjê przejœcia do strony logowania.
	 */
	String MY_ACCOUNT = "myaccount";

	/**
	 * Okreœla wynik akcji do przejœcia do zak³adki my account dla zalogowanego
	 * u¿ytkownika.
	 */
	String LOGGED_MY_ACCOUNT = "myaccount_logged";
	/**
	 * Czy operacja siê uda³a?
	 */
	String FAILED = "failed";
	/**
	 * Okreœla wynik dla akcji wyœwietlenia mieszkañ dla wybranego budynku.
	 */
	String SHOW_FLATS = "show_flats";

	/**
	 * IdŸ do administracji budynkami.
	 */
	String GO_BUILDING_ADMIN = "buildingAdmin";

	/**
	 * idŸ spowrotem.
	 */
	String BACK = "back";
	/**
	 * Sta³a okreœlaj¹ca akcjê wyœwietlenia listy osób dla potrzeb skojarzenia
	 * ich z u¿ytkownikami.
	 */
	String SHOW_PERSONS = "show_persons";
	/**
	 * Sta³¹ okreœlaj¹ca adres struts z list¹ budynków.
	 */
	String FIND_ADDRESS = "find_address";
	/**
	 * Wybrano proces do uruchomienia.
	 */
	String SUBMIT = "submit";

	String CANCEL = "cancel";

}
