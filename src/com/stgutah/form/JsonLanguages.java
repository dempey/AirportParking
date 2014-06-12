package com.stgutah.form;

import java.util.List;

import com.stgutah.model.Language;

public class JsonLanguages
{
	private List<Language> languages;

	public JsonLanguages(List<Language> languages)
	{
		super();
		this.languages = languages;
	}

	public List<Language> getLanguages()
	{
		return languages;
	}

	public void setLanguages(List<Language> languages)
	{
		this.languages = languages;
	}
}
