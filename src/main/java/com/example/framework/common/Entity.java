package com.example.framework.common;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * Abstract Persistance Model
 *
 * @date 2013. 4. 30. 오후 3:04:31
 * @version $Id$
 */
@SuppressWarnings("serial")
public abstract class Entity implements Serializable, Cloneable {

	@Override
	public Object clone() {
		Object obj = null;

		try {
			obj = super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		return obj;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	/**
	 * Reflection to String
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
