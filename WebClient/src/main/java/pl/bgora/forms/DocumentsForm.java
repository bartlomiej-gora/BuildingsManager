/**
 * 
 */
package pl.bgora.forms;

import com.github.bgora.beans.entity.Document;
import org.apache.struts.action.ActionForm;

import java.util.List;

/**
 * Klasa formatki dla dokument�w.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class DocumentsForm extends ActionForm {

	private static final long serialVersionUID = 594658908988988269L;

	private List<Document> documents;

	/**
	 * Zwraca warto�� pola documents
	 * 
	 * @return Warto�� pola documents
	 */
	public final List<Document> getDocuments() {
		return documents;
	}

	/**
	 * Ustawia warto�� pola documents
	 * 
	 * @param documents
	 *            Nowa warto�� pola documents
	 */
	public final void setDocuments(List<Document> documents) {
		this.documents = documents;
	}
}
