package com.example.framework.cache.support.validator;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 
 *  Abstract Data Validator
 *
 * @date 2013. 5. 8. 오후 4:59:08
 * @version $Id$
 */
public final class DataValidatorFactory {

	private static final Map<String, DataValidator> _VALIDATOR_POOL = new HashMap<String, DataValidator>();
	
	// The Constructor
	private DataValidatorFactory() {}
	
	/**
	 * Get DataValidator
	 *
	 * @param clazz Validator Class
	 * @return Validator Instance
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static synchronized DataValidator getValidator(Class<? extends DataValidator> clazz) throws InstantiationException, IllegalAccessException {
		DataValidator dataValidator = null;
		
		if (_VALIDATOR_POOL.containsKey(clazz.getName())) {
			dataValidator = _VALIDATOR_POOL.get(clazz.getName());
		} else {
			dataValidator = clazz.newInstance();
		}
		
		return dataValidator;
	}
	
	/**
	 * Clear Pool
	 *
	 */
	public static synchronized void clear() {
		_VALIDATOR_POOL.clear();
	}
	
	/**
	 * Get Pool Empty Status
	 *
	 * @return Status
	 */
	public static synchronized boolean isEmpty() {
		return _VALIDATOR_POOL.isEmpty();
	}
	
	/**
	 * Contains Validator
	 *
	 * @param className Validator Class Name
	 * @return Is Contained
	 */
	public static synchronized boolean contains(String className) {
		return _VALIDATOR_POOL.containsKey(className);
	}
	
	/**
	 * Remove Validator from Pool
	 *
	 * @param className Validator Class Name
	 */
	public static synchronized void remove(String className) {
		_VALIDATOR_POOL.remove(className);
	}
	
	/**
	 * Get Validator Name List
	 *
	 * @return Validator Name List
	 */
	public static synchronized Iterator<String> names() {
		return _VALIDATOR_POOL.keySet().iterator();
	}
	
	/**
	 * Get Pool Size
	 *
	 * @return Pool Size
	 */
	public static synchronized int size() {
		return _VALIDATOR_POOL.size();
	}
}
