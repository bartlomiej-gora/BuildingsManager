/**
 * 
 */
package com.github.bgora.beans.session.bussiness;

import com.github.bgora.beans.entity.Document;

import java.util.List;

/**
 * Interfejs biznesowy dla session beana zarz�dzaj�cego dokumentami.
 * 
 * @author Bart�omiej G�ra
 * 
 */
public interface DocumentBeanService extends AbstractBeanService<Document> {

	List<Document> getDocuments();
}
