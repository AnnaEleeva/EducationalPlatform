package com.project.EducationalPlatform.controller;

import com.project.EducationalPlatform.SubjectEnum;
import com.project.EducationalPlatform.levels.Levels;
import com.project.EducationalPlatform.levels.LevelsService;
import com.project.EducationalPlatform.quest.QuestMaker;
import com.project.EducationalPlatform.subjects.Subjects;
import com.project.EducationalPlatform.subjects.SubjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LevelsController {

	public static int level;

	@Autowired
	private LevelsService levelsService;

	@Autowired
	private SubjectsService subjectsService;

	@RequestMapping("/{userId}/sub/select/{idSubject}")
	public ModelAndView viewLevelsPage(@PathVariable(name = "userId") Long userId,
			@PathVariable(name = "idSubject") int idSubject) { // вывод таблицы

		SubjectsController.subjectEnum = SubjectEnum.values()[idSubject - 1];
		QuestMaker.fw();
		System.out.println();

		ModelAndView mav = new ModelAndView("levels");
		List<Levels> listLevels = levelsService.listAll();
		Subjects subject = subjectsService.get(idSubject);

		mav.addObject("listLevels", listLevels);
		mav.addObject("nameSubject", subject.getName());
		mav.addObject("idSubject", subject.getId());
		mav.addObject("userId", userId);

		return mav;
	}
	
	
	@RequestMapping("/{userId}/sub/select/{idSubject}/level/select/{levelId}/words")
	public ModelAndView viewLevelWordList(@PathVariable(name = "userId") Long userId,
			@PathVariable(name = "idSubject") int idSubject,
			@PathVariable(name = "levelId") int levelId) { // вывод таблицы


		ArrayList<String> listWordsRu = new ArrayList<String>();
		try {
			File file = new File(
					"C:\\My_Files\\Spring_work\\col_version\\src\\main\\resources\\txtFiles\\tests\\ru\\"
							+ levelId + "ru.txt");
			FileReader fr = new FileReader(file);
			BufferedReader reader = new BufferedReader(fr);
			String line = reader.readLine();
			while (line != null) {
				listWordsRu .add(line); // ru0 ru1
				line = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		

		ArrayList<String> listWordsDe = new ArrayList<String>();
		try {
			File file2 = new File(
					"C:\\My_Files\\Spring_work\\col_version\\src\\main\\resources\\txtFiles\\tests\\"
							+ SubjectsController.subjectEnum.getTitle() + "\\" + levelId
							+ SubjectsController.subjectEnum.getTitle() + ".txt");
			FileReader fr2 = new FileReader(file2);
			BufferedReader reader2 = new BufferedReader(fr2);
			String line2 = reader2.readLine();
			while (line2 != null) {

				listWordsDe.add(line2); // ru0 ru1
				line2 = reader2.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		ModelAndView mav = new ModelAndView("level_words");

		mav.addObject("listWordsRu", listWordsRu);
		mav.addObject("listWordsDe", listWordsDe);
		mav.addObject("userId", userId);
		mav.addObject("levelId", levelId);

		return mav;
	}
}
