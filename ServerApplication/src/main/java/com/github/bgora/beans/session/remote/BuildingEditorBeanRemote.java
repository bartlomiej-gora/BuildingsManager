package com.github.bgora.beans.session.remote;

import com.github.bgora.beans.session.bussiness.BuildingEditorService;

import javax.ejb.Remote;

/**
 * Zdalny interfejs dla edytora budynk�w.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
@Remote
public interface BuildingEditorBeanRemote extends BuildingEditorService {

}
