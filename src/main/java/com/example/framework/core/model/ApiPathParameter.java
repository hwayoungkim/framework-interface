package com.example.framework.core.model;

import com.example.framework.common.Entity;


/**
 * 
 * Open API Path Parameter Value Object
 * 
 * @date 2013. 4. 30. 오후 3:05:23
 * @version $Id$
 */
public class ApiPathParameter extends Entity implements Comparable<ApiPathParameter> {

	private static final long serialVersionUID = -522443116467234946L;
	private String regExp, name;
	private int index;

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
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index
	 *            the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(ApiPathParameter sepp) {
		return this.index - sepp.getIndex();
	}

}
