package com.github.bgora.beans.session.remote;

import com.github.bgora.beans.session.bussiness.CityBeanService;

import javax.ejb.Remote;

/**
 * Zdalny interfejs dla session beana do obs�ugi miast.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
@Remote
public interface CityBeanRemote extends CityBeanService {

}
