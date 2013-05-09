package com.example.web.controller.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import com.example.framework.cache.service.RegenerateService;
import com.example.framework.cache.support.validator.DataValidator;
import com.example.framework.cache.support.validator.DataValidatorFactory;
import com.example.framework.cache.support.validator.ServiceException;
import com.example.framework.cache.support.validator.ValidationException;
import com.example.framework.cache.support.validator.impl.HasApiValidator;
import com.example.framework.cache.support.validator.impl.RegisterApiValidator;
import com.example.framework.core.ConfigurationLoader;
import com.example.framework.core.model.Api;
import com.example.framework.core.util.JsonView;

/**
 * Api Controller
 *
 * @date 2013. 4. 29. 오후 5:00:32
 * @version $Id$
 */
@Controller
@RequestMapping(value="/framework/config")
public class ConfigurationController {

	@Autowired
	private ConfigurationLoader configurationLoader;

	@Autowired
	private RegenerateService regenerateService;

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationController.class);

	/**
	 * Apis List
	 *
	 * @param mav ModelAndView
	 * @return Forward View
	 */
	@RequestMapping(value = "/apis", method = RequestMethod.GET)
	public ModelAndView list(ModelAndView mav, HttpServletResponse response) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
	    resultMap.put("list", configurationLoader.getApiList());
	    return JsonView.Render(resultMap, response);
	}

	
	/**
	 * get Api 
	 *
	 * @param mav ModelAndView
	 * @return Forward View
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@RequestMapping(value = "/apis/{apiId}", method = RequestMethod.GET)
	public ModelAndView getApi(ModelAndView mav, HttpServletResponse response,@PathVariable String apiId) throws InstantiationException, IllegalAccessException {

		DataValidator validator = DataValidatorFactory.getValidator(HasApiValidator.class);

		if (!validator.validate(configurationLoader, null, apiId)) {
			throw new ValidationException();
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
	    resultMap.put("api", configurationLoader.getApi(apiId));
	    return JsonView.Render(resultMap, response);
	}
	
	/**
	 *  Register Api
	 *
	 * @param mav ModelAndView
	 * @return Forward View
	 */
	@RequestMapping(value = "/apis", method =  RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void registerApi(@RequestBody String receiveData, ModelAndView mav
			,HttpServletRequest request) throws InstantiationException, IllegalAccessException {

		JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(receiveData);
		Api api= regenerateService.registerApi(jsonObject);
		
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Response Register Open API Data : {}", api);
		}

		DataValidator validator = DataValidatorFactory.getValidator(RegisterApiValidator.class);

		if (validator.validate(configurationLoader, api)) {
			configurationLoader.setApi(api);
		} else {
			throw new ValidationException();
		}
	}

	
	/**
	 * Modify Api
	 *
	 * @param mav ModelAndView
	 * @return Forward View
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@RequestMapping(value="/apis/{apiId}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void modifyApi(@RequestBody String receiveData, ModelAndView mav, @PathVariable String apiId) throws InstantiationException, IllegalAccessException {
			
		JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(receiveData);
		Api api= regenerateService.registerApi(jsonObject);

		if (LOGGER.isInfoEnabled()) {
				LOGGER.info("Response Modify Open API Data : {}", api);
		}

		DataValidator validator = DataValidatorFactory.getValidator(HasApiValidator.class);

		if (!validator.validate(configurationLoader, null, apiId)) {
			throw new ValidationException();
		} else {
			configurationLoader.setApi(api);
		}
	}

	/**
	 * Delete Api
	 *
	 * @param mav ModelAndView
	 * @return Forward View
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@RequestMapping(value="/apis/{apiId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteApi(ModelAndView mav, @PathVariable String apiId) throws InstantiationException, IllegalAccessException {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Delete Open API ID : {}", apiId);
		}

		DataValidator validator = DataValidatorFactory.getValidator(HasApiValidator.class);

		if (!validator.validate(configurationLoader, null, apiId)) {
			throw new ValidationException();
		}

		boolean ret = configurationLoader.removeApi(apiId);
		if (!ret) {
			throw new ServiceException();
		}
	}

}
