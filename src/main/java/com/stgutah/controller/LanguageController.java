package com.stgutah.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stgutah.form.JsonLanguages;
import com.stgutah.service.ModelService;

@Controller
@RequestMapping("/language")
public class LanguageController
{
	private static Logger logger = Logger.getLogger(LanguageController.class);

	@Autowired
	private ModelService modelService;

	@RequestMapping(value = "/languagepage", method = RequestMethod.GET)
	public String getLanguagePage()
	{
		String username = modelService.getCurrentSessionUser().getUserName();

		logger.debug("Received request to show " + username + "'s language selection page");

		return "language";
	}

	@RequestMapping(value="/languagelist", method = RequestMethod.GET)
	public @ResponseBody JsonLanguages getLanguageList() throws Exception
	{
		JsonLanguages languages = modelService.getLanguagesAsJsonObject();
		return languages;
	}

	@RequestMapping(value = "/submitdata", method = RequestMethod.POST)
	public void persistSubmittedData() throws Exception
	{
		logger.debug("Received submitted data");
	}
}