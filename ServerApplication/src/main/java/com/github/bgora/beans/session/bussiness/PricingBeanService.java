package com.github.bgora.beans.session.bussiness;

import com.github.bgora.beans.entity.Building;
import com.github.bgora.beans.entity.Pricing;

import java.rmi.RemoteException;

/**
 * Metoday biznesowe dla beana bezstanowego dla schemat�w cenowych budynk�w.
 * 
 * @author Bart�omiej G�ra
 * 
 */
public interface PricingBeanService extends AbstractBeanService<Pricing> {

	/**
	 * Zwraca schemat cenowy dla podanego budynku.
	 * 
	 * @param building
	 *            budynek dla kt�rego chcemy mie� schemat cenowy.
	 * @return schemat cenowy
	 * @throws RemoteException
	 *             Wyj�tek Jbossa.
	 */
	Pricing getPricingForBuilding(Building building) throws RemoteException;
}
