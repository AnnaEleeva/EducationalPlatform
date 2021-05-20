package com.project.EducationalPlatform.controller;

import com.project.EducationalPlatform.quest.QuestMaker;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
public class TestController {

	private int nowQuestion = 0; // we increment this after each question (after pushing OK)
	private int correctAnswers = 0;

	ArrayList<String> ru1 = new ArrayList<>(Arrays.asList("один", "два", "три"));
	ArrayList<String> en1 = new ArrayList<>(Arrays.asList("one", "two", "three"));

/*	@RequestMapping("/{userId}/sub/select/{idSubject}/level/select/{idLevel}/ver/select/{idVersion}")
	public ModelAndView viewTestPage(@PathVariable(name = "idSubject") int idSubject,
			@PathVariable(name = "idLevel") int idLevel, @PathVariable(name = "idVersion") int idVersion) { // вывод
																											// таблицы
		ModelAndView mav = new ModelAndView("test");

		// mav.addObject("listLevels", listLevels);
		// mav.addObject("nameSubject",subject.getName());
		mav.addObject("ruList", ru1);
		mav.addObject("deList", en1);

		return mav;
	}*/

	@RequestMapping(value = "/getAnswer", method = RequestMethod.POST)
	public String reSet(@ModelAttribute("answer") String str) {
		System.out.println(str);
		return "index";
	}

	private List<String> stringList;

	@GetMapping("/{userId}/sub/select/{idSubject}/level/select/{idLevel}/ver/select/{idVersion}")
	@ResponseBody
	public ModelAndView getFoos(@RequestParam(required = false) Integer contact,
			@PathVariable(name = "idLevel") int idLevel) {

		ModelAndView mav = new ModelAndView("test");
		if (contact == null) { // если ответа на тест не было, то просто отображаем форму

			// mav.addObject("listLevels", listLevels);
			// mav.addObject("nameSubject",subject.getName());

			nowQuestion = 0;
			correctAnswers = 0;

			// questionText.setText(QuestMaker.questions.get(idLevel).get(nowQuestion).getQuestion());
			// System.out.println("LEVEL "+idLevel);
			// System.out.println("lEv "+ LevelsController.level);
			String[] answers = QuestMaker.questions.get(LevelsController.level-1).get(nowQuestion).getAnswers();
			stringList = Arrays.asList(answers);
			Collections.shuffle(stringList);

			/*
			 * answerRadio1.setText(stringList.get(0));
			 * answerRadio2.setText(stringList.get(1));
			 * answerRadio3.setText(stringList.get(2));
			 */

			mav.addObject("questionText",
					QuestMaker.questions.get(LevelsController.level-1).get(nowQuestion).getQuestion());
			mav.addObject("stringList", stringList);

		} else {

			if (stringList.get(contact)
					.equals(QuestMaker.questions.get(LevelsController.level-1).get(nowQuestion).getKey())) {
				System.out.println("Good!");
				correctAnswers++;
			} else {
				System.out.println("Bad");
			}
			if (nowQuestion + 1 != QuestMaker.questions.get(LevelsController.level-1).size()) {
				nowQuestion++;
				String[] answers = QuestMaker.questions.get(LevelsController.level-1).get(nowQuestion).getAnswers();
				stringList = Arrays.asList(answers);
				Collections.shuffle(stringList);

				mav.addObject("questionText",
						QuestMaker.questions.get(LevelsController.level-1).get(nowQuestion).getQuestion());
				mav.addObject("stringList", stringList);

			} else {

				mav = new ModelAndView("result");

				mav.addObject("resText", "Правильных ответов: " + correctAnswers + " из "
						+ QuestMaker.questions.get(LevelsController.level-1).size());

			}

			/*
			 * mav.addObject("ruList",ru1); mav.addObject("deList",en1);
			 * System.out.println("ОПА! "+contact); mav.addObject("answer",contact);
			 */
		}

		return mav;
	}
}
