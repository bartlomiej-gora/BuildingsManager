/**
 * 
 */
package pl.bgora.actions;

import com.github.bgora.beans.entity.Document;
import com.github.bgora.beans.session.bussiness.DocumentBeanService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import pl.bgora.forms.DocumentsForm;
import pl.bgora.utils.ActionResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Akcja Wy�wietlania dokument�w.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class DocumentsAction extends Action {

	private DocumentBeanService documentService;

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public final ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception { // NOSONAR
		DocumentsForm documentsForm = (DocumentsForm) form; // NOSONAR
		List<Document> documents = documentService.getDocuments();
		documentsForm.setDocuments(documents);
		return mapping.findForward(ActionResult.SUCCESS);
	}

	/**
	 * Zwraca warto�� pola documentService
	 * 
	 * @return Warto�� pola documentService
	 */
	public final DocumentBeanService getDocumentService() {
		return documentService;
	}

	/**
	 * Ustawia warto�� pola documentService
	 * 
	 * @param documentService
	 *            Nowa warto�� pola documentService
	 */
	public final void setDocumentService(DocumentBeanService documentService) {
		this.documentService = documentService; // NOSONAR
	}
}
