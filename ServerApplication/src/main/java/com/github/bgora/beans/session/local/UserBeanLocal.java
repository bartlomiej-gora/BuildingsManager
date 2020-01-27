package com.github.bgora.beans.session.local;

import com.github.bgora.beans.session.bussiness.UserBeanService;

import javax.ejb.Local;

/**
 * Lokalny interfej dla U�ytkownik�w.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
@Local
public interface UserBeanLocal extends UserBeanService {

}
