/**
 * 
 */
package pl.bgora.forms;

import com.github.bgora.beans.entity.Flat;
import com.github.bgora.beans.session.bussiness.BuildingEditorService;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import pl.bgora.forms.decorator.FlatWrapper;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Formatka wy�iwetlania listy mieszka�.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class FlatAdminForm extends ActionForm {

	private static final long serialVersionUID = 1262832025757378931L;

	private String buildingName;

	private BuildingEditorService editor;

	private static Logger logger = Logger.getLogger(FlatAdminForm.class);

	/**
	 * Zwraca warto�� pola flats
	 * 
	 * @return Warto�� pola flats
	 */
	public List<FlatWrapper> getFlats() {
		assert editor != null : "Editor nie mo�e by� null!";
		try {
			List<Flat> flats = editor.getFlats();
			List<FlatWrapper> wrappers = new ArrayList<FlatWrapper>();
			for (Flat flat : flats) {
				wrappers.add(new FlatWrapper(flat));
			}
			return wrappers;
		} catch (RemoteException e) {
			logger.error(e);
			// Nigdy nie mo�e by� pustej listy tutaj!
			return new ArrayList<FlatWrapper>();
		}
	}

	/**
	 * Ustawia warto�� pola flats
	 * 
	 * @param flats
	 *            Nowa warto�� pola flats
	 * @throws RemoteException
	 */
	public void setFlats(List<Flat> flats) {
		assert editor != null : "Editor nie mo�e by� null!";
		try {
			this.editor.setFlats(flats);
		} catch (RemoteException e) {
			logger.error(e);
		}
	}

	/**
	 * Zwraca warto�� pola buildingName
	 * 
	 * @return Warto�� pola buildingName
	 */
	public String getBuildingName() {
		return buildingName;
	}

	/**
	 * Ustawia warto�� pola buildingName
	 * 
	 * @param buildingName
	 *            Nowa warto�� pola buildingName
	 */
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	/**
	 * Zwraca warto�� pola editor
	 * 
	 * @return Warto�� pola editor
	 */
	public BuildingEditorService getEditor() {
		return editor;
	}

	/**
	 * Ustawia warto�� pola editor
	 * 
	 * @param editor
	 *            Nowa warto�� pola editor
	 */
	public void setEditor(BuildingEditorService editor) {
		this.editor = editor;
	}

}
