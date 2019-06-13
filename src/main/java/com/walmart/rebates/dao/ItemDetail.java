package com.walmart.rebates.dao;
import java.io.Serializable;

import javax.persistence.*;
@Entity
@Table(name = "\"ItemDetail\"")
public class ItemDetail implements Serializable {

@GeneratedValue(strategy = GenerationType.AUTO)
@Id
@Column(name = "\"ItemNumber\"")
private int itemNumber;

public int getItemNumber() {
	return itemNumber;
}

public void setItemNumber(int itemNumber) {
	itemNumber = itemNumber;
}

@Column(name = "\"SalesQuantity\"")
private int salesQuantity;

@Column(name = "\"UOM\"")
private char uOM;

@Column(name = "\"SalesAmount\"")
private int salesAmt;

@Column(name = "\"Currency\"")
private String curr;

@Column(name = "\"ItemDescrption\"")
private String itemDes;

//@Column(name = "\"Department\"")
//private int Department;

//@Column(name = "\"Subclass\"")
//private int Subclass;

//@Column(name = "\"Fineline\"")
//private int Fineline;

//@Column(name = "\"DepartmentCategory\"")
//private int DepartmentCatg;

@Column(name = "\"VendorNumber\"")
private int vendorNum;

@ManyToOne
@JoinColumns({
@JoinColumn(name = "\"Department\"", referencedColumnName = "\"Department\"" ),
@JoinColumn(name = "\"Subclass\"", referencedColumnName = "\"Subclass\"" ),
@JoinColumn(name = "\"Fineline\"", referencedColumnName = "\"Fineline\"" ),
@JoinColumn(name = "\"DepartmentCategory\"", referencedColumnName = "\"DepartmentCategory\"" )
})
private MerchHier merhhier;

public int getVendorNum() {
	return vendorNum;
}

public void setVendorNum(int vendorNum) {
	vendorNum = vendorNum;
}

public String getItemDes() {
	return itemDes;
}

public void setItemDes(String itemDes) {
	itemDes = itemDes;
}

public String getCurr() {
	return curr;
}

public void setCurr(String curr) {
	curr = curr;
}

public int getSalesAmt() {
	return salesAmt;
}

public void setSalesAmt(int salesAmt) {
	salesAmt = salesAmt;
}

public char getUOM() {
	return uOM;
}

public void setUOM(char uOM) {
	uOM = uOM;
}


public int getSalesQuantity() {
	return salesQuantity;
}

public void setSalesQuantity(int salesQuantity) {
	salesQuantity = salesQuantity;
}


public MerchHier getMerhhier() {
	return merhhier;
}

public void setMerhhier(MerchHier merhhier) {
	this.merhhier = merhhier;
}




} 