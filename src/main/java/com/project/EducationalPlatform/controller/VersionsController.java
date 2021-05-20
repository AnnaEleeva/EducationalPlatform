package com.project.EducationalPlatform.controller;

import com.project.EducationalPlatform.levels.Levels;
import com.project.EducationalPlatform.levels.LevelsService;
import com.project.EducationalPlatform.subjects.Subjects;
import com.project.EducationalPlatform.subjects.SubjectsService;
import com.project.EducationalPlatform.versions.Versions;
import com.project.EducationalPlatform.versions.VersionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class VersionsController {

	@Autowired
	private SubjectsService subjectsService; // предмет

	@Autowired
	private LevelsService levelsService; // уровень

	@Autowired
	private VersionsService versionsService; // вариант обучения

	@RequestMapping("/{userId}/sub/select/{idSubject}/level/select/{idLevel}")
	public ModelAndView viewVersionsPage(@PathVariable(name = "idSubject") int idSubject,
			@PathVariable(name = "idLevel") int idLevel) { // вывод таблицы
		ModelAndView mav = new ModelAndView("versions");
		List<Versions> listVersions = versionsService.listAll();

		Subjects subject = subjectsService.get(idSubject); // знали предмет
		Levels level = levelsService.get(idLevel); // узнали уровень

		mav.addObject("listVersions", listVersions);

		mav.addObject("nameSubject", subject.getName());
		mav.addObject("idSubject", subject.getId());
		mav.addObject("idLevel", level.getId()); //

		LevelsController.level = idLevel;

		return mav;
	}
}
