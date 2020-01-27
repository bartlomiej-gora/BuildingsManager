package com.github.bgora.beans.session.remote;

import com.github.bgora.beans.session.bussiness.PricingBeanService;

import javax.ejb.Remote;

/**
 * Zdalny interfejs dla szablon�w cenowych budynk�w.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
@Remote
public interface PricingBeanRemote extends PricingBeanService {

}
