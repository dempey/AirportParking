package com.stgutah.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "AMOUNT")
public class Amount
{
	@Id
	@Column(name = "AMOUNT_ID")
	@GeneratedValue
	private Integer amountId;
	
	@ManyToOne
	@JoinColumn(name = "AMOUNT_TYPE_ID", nullable = false)
	@ForeignKey(name = "AMOUNT_TYPE_FK")
	private AmountType amountType;
	
	@ManyToOne
	@JoinColumn(name = "PARKING_LOT_ID", nullable = false)
	@ForeignKey(name = "AMOUNT_PARKING_LOT_FK")
	private ParkingLot parkingLot;
	
	@Column(name = "AMOUNT", nullable = false)
	private Float amount;
	
	public Integer getAmountId()
	{
		return amountId;
	}
	
	public void setAmountId(Integer amountId)
	{
		this.amountId = amountId;
	}
	
	public AmountType getAmountType()
	{
		return amountType;
	}
	
	public void setAmountType(AmountType amountType)
	{
		this.amountType = amountType;
	}
	
	public ParkingLot getParkingLot()
	{
		return parkingLot;
	}

	public void setParkingLot(ParkingLot parkingLot)
	{
		this.parkingLot = parkingLot;
	}
	
	public Float getAmount()
	{
		return amount;
	}
	
	public void setAmount(Float amount)
	{
		this.amount = amount;
	}
}