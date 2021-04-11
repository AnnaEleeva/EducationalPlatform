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

import java.util.List;

@Controller
public class LevelsController {

    public static int level;

    @Autowired
    private LevelsService levelsService;

    @Autowired
    private SubjectsService subjectsService;

    @RequestMapping("/sub/select/{idSubject}")
    public ModelAndView viewLevelsPage(@PathVariable(name = "idSubject") int idSubject) { //вывод таблицы

        SubjectsController.subjectEnum= SubjectEnum.values()[idSubject-1];
        QuestMaker.fw();
        System.out.println();

        ModelAndView mav = new ModelAndView("levels");
        List<Levels> listLevels = levelsService.listAll();
        Subjects subject =subjectsService.get(idSubject);

        mav.addObject("listLevels", listLevels);
        mav.addObject("nameSubject",subject.getName());
        mav.addObject("idSubject",subject.getId());

        return mav;
    }
}
