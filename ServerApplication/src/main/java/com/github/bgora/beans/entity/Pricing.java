/**
 * 
 */
package com.github.bgora.beans.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Klasa odzwierciedlaj�ca Schemat ceny w danym budynku.
 * 
 * @author Bart�omiej G�ra
 * 
 */
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@Entity
@Table(name = "Pricing")
@SequenceGenerator(name = "PricingSequence", sequenceName = "PricingSequence", initialValue = 0, allocationSize = 1)
@NamedQueries(value = {
		@NamedQuery(name = "getPricingForBuildingCount", query = "SELECT COUNT(p) FROM Pricing p WHERE p.building = :building"),
		@NamedQuery(name = "getPricingForBuilding", query = "SELECT p FROM Pricing p WHERE p.building = :building") })
public class Pricing implements Serializable {

	private static final long serialVersionUID = 5126859539879378741L;

	private Long pricingId;

	private Double metterPrice;

	private Double coldWater;

	private Double hotWatter;

	private Double repairFund;

	private Double garbagePrice;

	private Building building;

	@Id
	@GeneratedValue(generator = "PricingSequence", strategy = GenerationType.SEQUENCE)
	@Column(name = "pricingId")
	public Long getPricingId() {
		return pricingId;
	}

	@Column(name = "price_per_metter")
	public Double getMetterPrice() {
		return metterPrice;
	}

	@Column(name = "cold_water_price")
	public Double getColdWater() {
		return coldWater;
	}

	@Column(name = "hot_water_price")
	public Double getHotWatter() {
		return hotWatter;
	}

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH })
	@JoinColumn(name = "building_id")
	public Building getBuilding() {
		return building;
	}

	@Column(name = "repair_fund")
	public Double getRepairFund() {
		return repairFund;
	}

	@Column(name = "garbage_collection")
	public Double getGarbagePrice() {
		return garbagePrice;
	}

	public void setPricingId(Long pricingId) {
		this.pricingId = pricingId;
	}

	public void setMetterPrice(Double metterPrice) {
		this.metterPrice = metterPrice;
	}

	public void setHotWatter(Double hotWatter) {
		this.hotWatter = hotWatter;
	}

	public void setRepairFund(Double repairFund) {
		this.repairFund = repairFund;
	}

	public void setGarbagePrice(Double garbagePrice) {
		this.garbagePrice = garbagePrice;
	}

	public void setColdWater(Double coldWater) {
		this.coldWater = coldWater;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}
}
