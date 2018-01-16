package com.fdmgroup.finalproject.model;

import java.math.BigDecimal;

import javax.persistence.*;


@Entity
@Table (name="coupon_finalp")
public class Coupon {

	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="couponID")
	private int couponID;
	@Column(name="description")
	private String description;
	@Column(name="value")
	private BigDecimal value;
	@Column(name="uses")
	private int uses;
	
	public Coupon() {
		super();
	}
	
	public Coupon(String description, BigDecimal value, int uses) {
		super();
		this.description = description;
		this.value = value;
		this.uses = uses;
	}

	public int getCouponID() {
		return couponID;
	}

	public void setCouponID(int couponID) {
		this.couponID = couponID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public int getUses() {
		return uses;
	}

	public void setUses(int uses) {
		this.uses = uses;
	}	
}
