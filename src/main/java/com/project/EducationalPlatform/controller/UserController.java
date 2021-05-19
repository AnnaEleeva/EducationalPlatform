package com.project.EducationalPlatform.controller;

import java.util.ArrayList;
import java.util.List;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.users.responses.GetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.project.EducationalPlatform.users.User;
import com.project.EducationalPlatform.users.UserService;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	TransportClient transportClient = HttpTransportClient.getInstance();
	VkApiClient vk = new VkApiClient(transportClient);
		
	@RequestMapping("/")
	public String viewHomePage(Model model, @RequestParam("user") String user, @RequestParam("token") String token, HttpServletResponse response) { //вывод таблицы


		System.out.println("мы в info");
		System.out.println("access_token: "+token);
		try {
			UserActor actor = new UserActor(Integer.parseInt(user), token);
			List<GetResponse> getUsersResponse = vk.users().get(actor).userIds(user).execute();
			GetResponse userR = getUsersResponse.get(0);

			String email= userR.getEmail();
			String phone=userR.getMobilePhone();
			String first_name=userR.getFirstName();
			String last_name=userR.getLastName();
			model.addAttribute("first_name_last_name", first_name+" "+last_name);
			Long vkId= Long.valueOf(userR.getId());
			model.addAttribute("vkId", vkId);

			User tempUser=new User(vkId,first_name,last_name);


			userService.save(tempUser);



		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
			response.setContentType("text/html;charset=utf-8");
			e.printStackTrace();
		}
		User tempUser =  userService.get(Long.parseLong(user));
		List<User> listUsers = new ArrayList<User>();
		listUsers.add(tempUser);
		model.addAttribute("listUsers", listUsers);
		
		return "index";
	}

	@RequestMapping("/login")
	public String viewLoginPage(Model model) { //вывод таблицы
		return "login";
	}


}
