package com.project.EducationalPlatform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.project.EducationalPlatform.users.User;
import com.project.EducationalPlatform.users.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
		
	@RequestMapping("/")
	public String viewHomePage(Model model) { //вывод таблицы
		List<User> listUsers = userService.listAll();
		model.addAttribute("listUsers", listUsers);
		
		return "index";
	}
}
