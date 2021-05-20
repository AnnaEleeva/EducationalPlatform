package com.project.EducationalPlatform;

public enum SubjectEnum {
	EN("en"), DE("de");

	private String title;

	SubjectEnum(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}
}
