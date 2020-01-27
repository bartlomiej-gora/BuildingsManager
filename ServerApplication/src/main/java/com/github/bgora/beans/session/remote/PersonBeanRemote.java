package com.github.bgora.beans.session.remote;

import com.github.bgora.beans.session.bussiness.PersonBeanService;

import javax.ejb.Remote;

/**
 * Zdalny interfejs dla os�b
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
@Remote
public interface PersonBeanRemote extends PersonBeanService {

}
