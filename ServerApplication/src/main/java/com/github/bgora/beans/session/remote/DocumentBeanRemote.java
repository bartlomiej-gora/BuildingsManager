package com.github.bgora.beans.session.remote;

import com.github.bgora.beans.session.bussiness.DocumentBeanService;

import javax.ejb.Remote;

/**
 * Zdalny interfejs dla dokument�w.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
@Remote
public interface DocumentBeanRemote extends DocumentBeanService {

}
