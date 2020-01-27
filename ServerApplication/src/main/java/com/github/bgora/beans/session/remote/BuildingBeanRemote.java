package com.github.bgora.beans.session.remote;

import com.github.bgora.beans.session.bussiness.BuildingBeanService;

import javax.ejb.Remote;

/**
 * Zdalny interfejs do obs�ugi budynk�w.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
@Remote
public interface BuildingBeanRemote extends BuildingBeanService {

}
