package com.walmart.rebates.dao;

import java.io.Serializable;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@IdClass(MerchHierKeys.class)
@Table(name = "\"MerchHier\"")
public class MerchHier implements Serializable {
	
	@Id
	@Column(name = "\"Department\"")
	private int department;

	@Id
	@Column(name = "\"Subclass\"")
	private int subclass;

	@Id
	@Column(name = "\"Fineline\"")
	private int fineline;

	@Id
	@Column(name ="\"DepartmentCategory\"")
	private int departmentCatg;
     
	@Column(name = "\"DepartmentDesc\"")
	private String departmentDesc;
	
	@Column(name = "\"SubclassDesc\"")
	private String subclaaDesc;
	
	@Column(name = "\"FinlineDesc\"")
	private String finlinetDesc;
	
	@Column(name = "\"DeptCategoryDesc\"")
	private String deptCatgDesc;

	public int getDepartment() {
		return department;
	}

	public void setDepartment(int department) {
		this.department = department;
	}

	public int getSubclass() {
		return subclass;
	}

	public void setSubclass(int subclass) {
		this.subclass = subclass;
	}

	public int getFineline() {
		return fineline;
	}

	public void setFineline(int fineline) {
		this.fineline = fineline;
	}

	public int getDepartmentCatg() {
		return departmentCatg;
	}

	public void setDepartmentCatg(int departmentCatg) {
		this.departmentCatg = departmentCatg;
	}

	public String getDepartmentDesc() {
		return departmentDesc;
	}

	public void setDepartmentDesc(String departmentDesc) {
		this.departmentDesc = departmentDesc;
	}

	public String getSubclaaDesc() {
		return subclaaDesc;
	}

	public void setSubclaaDesc(String subclaaDesc) {
		this.subclaaDesc = subclaaDesc;
	}

	public String getFinlinetDesc() {
		return finlinetDesc;
	}

	public void setFinlinetDesc(String finlinetDesc) {
		this.finlinetDesc = finlinetDesc;
	}

	public String getDeptCatgDesc() {
		return deptCatgDesc;
	}

	public void setDeptCatgDesc(String deptCatgDesc) {
		this.deptCatgDesc = deptCatgDesc;
	}
	
	
	
} 