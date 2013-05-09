package com.example.web.controller.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * OpenApi Controller
 *
 * @date 2013. 4. 29. 오후 5:00:32
 * @version $Id$
 */
@Controller
@RequestMapping(value="/openApi")
public class OpenApiController {

	/**
	 * OpenApi List
	 *
	 * @param mav ModelAndView
	 * @return Forward View
	 */
	@RequestMapping(value = "/getOpenApiList")
	public ModelAndView getOpenApiList(ModelAndView mav) {
		mav.setViewName("getOpenApiList");
		return mav;
	}


	/**
	 * OpenApi Detail
	 *
	 * @param mav ModelAndView
	 * @return Forward View
	 */
	@RequestMapping(value = "/getOpenApiDetail")
	public ModelAndView getOpenApiDetail(ModelAndView mav) {
		mav.setViewName("getOpenApiDetail");
		return mav;
	}
	
	
	/**
	 *  Register Api View
	 *
	 * @param mav ModelAndView
	 * @return Forward View
	 */
	@RequestMapping(value = "/createOpenApi", method = RequestMethod.GET)
	public ModelAndView createOpenApi(ModelAndView mav) {
		mav.setViewName("createOpenApi");
		return mav;
	}

	/**
	 *  modify Api
	 *
	 * @param mav ModelAndView
	 * @return Forward View
	 */
	@RequestMapping(value = "/modifyOpenApi", method = RequestMethod.GET)
	public ModelAndView modifyOpenApiView(ModelAndView mav) {
		mav.setViewName("modifyOpenApi");
		return mav;
	}

	/**
	 *  test
	 *
	 * @param mav ModelAndView
	 * @return Forward View
	 */
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ModelAndView test(ModelAndView mav) {
		System.out.println("_______________");
		mav.setViewName("test");
		return mav;
	}

	
	
}
