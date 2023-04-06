package com.smart.controllers;

import java.security.Principal;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smart.entities.Contacts;
import com.smart.entities.User;
import com.smart.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository repository;
	
	//method for adding common data to response
	@ModelAttribute
	public void addCommandData(Model model,Principal principal) {
		String username=principal.getName();
		System.out.println("USERNAME"+username);
		//get the user using user name(email)
		User user=repository.getUserByUserName(username);
		System.out.println("USER"+user);
		model.addAttribute("user", user);
	
		
	}
	
	public UserController() {
		// TODO Auto-generated constructor stub
	}
	
	//dash board home
	@RequestMapping("/index")
	public String dashboard(Model model,Principal principal) 
	{
		model.addAttribute("title","dash board");
		return "normal/user_dashboard";
	}
	
	//open add form handler
	@GetMapping("/add-contact")
	public String opebAddContactForm(Model model,Principal principal) {
		model.addAttribute("title","Add contact");
		model.addAttribute("contact",new Contacts());
		return "normal/add_contact_form";
	}

}
