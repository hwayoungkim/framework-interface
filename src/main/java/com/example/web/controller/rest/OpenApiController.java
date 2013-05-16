package com.example.web.controller.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.server.ServerHttpRequest;
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
	@RequestMapping(value = "/getOpenApiDetail", method = RequestMethod.GET)
	public ModelAndView getOpenApiDetail(ModelAndView mav,HttpServletRequest request) {
		mav.setViewName("getOpenApiDetail");
		String apiId = request.getParameter("apiId");
		mav.addObject("apiId", apiId);
		return mav;
	}
	
	
	/**
	 *  Register Api View
	 *
	 * @param mav ModelAndView
	 * @return Forward View
	 */
	@RequestMapping(value = "/registerOpenApi", method = RequestMethod.GET)
	public ModelAndView createOpenApi(ModelAndView mav) {
		mav.setViewName("registerOpenApi");
		return mav;
	}

	/**
	 *  modify Api
	 *
	 * @param mav ModelAndView
	 * @return Forward View
	 */
	@RequestMapping(value = "/modifyOpenApi", method = RequestMethod.GET)
	public ModelAndView modifyOpenApiView(ModelAndView mav, HttpServletRequest request) {
		mav.setViewName("modifyOpenApi");
		String apiId = request.getParameter("apiId");
		mav.addObject("apiId", apiId);
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
