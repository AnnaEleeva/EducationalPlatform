package com.project.EducationalPlatform.controller;

import com.project.EducationalPlatform.product.Product;
import com.project.EducationalPlatform.product.ProductService;
import com.project.EducationalPlatform.subjects.SubjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AppController {

	@Autowired
	private ProductService service;

	@Autowired
	private SubjectsService subjectsService;
	
	@RequestMapping("/")
	public String viewHomePage(Model model) { //вывод таблицы
		List<Product> listProducts = service.listAll();
		model.addAttribute("listProducts", listProducts);
		
		return "index";
	}

	/*@RequestMapping("/")
	public String viewHomePage(Model model) { //вывод таблицы
		List<Subjects> listSubjects = subjectsService.listAll();
		model.addAttribute("listSubjects", listSubjects);

		return "subjects";
	}*/
	
	@RequestMapping("/new")
	public String showNewProductPage(Model model) { //создание нового продукта
		Product product = new Product();
		model.addAttribute("product", product);
		
		return "new_product";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product) {
		service.save(product);
		
		return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("edit_product");//ModelAndView ( Вид, Указатель, Держатель );
		Product product = service.get(id);
		mav.addObject("product", product); //Указатель, Держатель
		
		return mav;
	}
	@RequestMapping("/play/{id}")
	public ModelAndView showPlayProductPage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("playView");
		Product product = service.get(id);
		mav.addObject("product", product);
		//mav.addObject("id",id);

		return mav;
		//return "playView";
	}

	
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") int id) {
		service.delete(id);
		return "redirect:/";		
	}
}
