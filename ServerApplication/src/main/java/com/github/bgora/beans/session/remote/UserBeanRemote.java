package com.github.bgora.beans.session.remote;

import com.github.bgora.beans.session.bussiness.UserBeanService;

import javax.ejb.Remote;

/**
 * Zdalny interfejs dla U�ytkownik�w.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
@Remote
public interface UserBeanRemote extends UserBeanService {

}
