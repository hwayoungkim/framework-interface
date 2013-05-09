package com.example.framework.core.model;

import com.example.framework.common.Entity;


/**
 * Open API Query String Value Object
 *
 * @date 2013. 4. 30. 오후 3:05:36
 * @version $Id$
 */
public class ApiQueryParameter extends Entity {

	private static final long serialVersionUID = -3809632204981867703L;
	private String name, regExp;
	private boolean mandatory;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the regExp
	 */
	public String getRegExp() {
		return regExp;
	}

	/**
	 * @param regExp
	 *            the regExp to set
	 */
	public void setRegExp(String regExp) {
		this.regExp = regExp;
	}

	/**
	 * @return the mandatory
	 */
	public boolean isMandatory() {
		return mandatory;
	}

	/**
	 * @param mandatory
	 *            the mandatory to set
	 */
	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}

}
