package com.project.EducationalPlatform.controller;

import com.project.EducationalPlatform.SubjectEnum;
import com.project.EducationalPlatform.subjects.Subjects;
import com.project.EducationalPlatform.subjects.SubjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class SubjectsController {

	public static SubjectEnum subjectEnum;

	@Autowired
	private SubjectsService subjectsService;

	@RequestMapping("/sub")
	public String viewSubjectsPage(Model model) { // вывод таблицы
		List<Subjects> listSubjects = subjectsService.listAll();
		model.addAttribute("listSubjects", listSubjects);

		return "subjects";
	}

	/*
	 * @RequestMapping("/sub/select/{id}") public String
	 * showPlayProductPage(@PathVariable(name = "id") int id) { ModelAndView mav =
	 * new ModelAndView("playView"); Subjects subject = subjectsService.get(id);
	 * mav.addObject("subject", subject); //mav.addObject("id",id);
	 * 
	 * return "index"; //return "playView"; }
	 */
}
