/**
 * 
 */
package com.github.bgora.beans.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Klasa odzwieciedlaj�c� konkretny rachunek za mieszkanie.
 * 
 * @author Bart�omiej G�ra
 * 
 */
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@Entity
@Table(name = "Rent")
@SequenceGenerator(name = "RentSequence", sequenceName = "RentSequence", initialValue = 0, allocationSize = 1)
public class Rent implements Serializable {

	private static final long serialVersionUID = 6691008667908888562L;

	private Long rentId;

	private Double metterPrice;

	private Double coldWater;

	private Double hotWatter;

	private Double repairFund;

	private Double garbagePrice;

	private Flat flat;

	@Id
	@GeneratedValue(generator = "RentSequence", strategy = GenerationType.SEQUENCE)
	@Column(name = "pricingId")
	public Long getRentId() {
		return rentId;
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

	@Column(name = "repair_fund")
	public Double getRepairFund() {
		return repairFund;
	}

	@Column(name = "garbage_collection")
	public Double getGarbagePrice() {
		return garbagePrice;
	}

	@OneToOne
	@JoinColumn(name = "flat_id")
	public Flat getFlat() {
		return flat;
	}

	public void setRentId(Long rentId) {
		this.rentId = rentId;
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

	public void setFlat(Flat flat) {
		this.flat = flat;
	}

}
