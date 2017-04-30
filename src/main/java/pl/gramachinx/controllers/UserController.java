package pl.gramachinx.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import pl.gramachinx.services.CheckAuthorize;

@Controller
public class UserController {
	
	@Autowired
	CheckAuthorize checkauth;
	
	
	@GetMapping("/user")
	public String userPage()
	{
		if(!checkauth.ifAuthorized(SecurityContextHolder.getContext().getAuthentication().getName()))
		{
		return "activatedPage";	//TODO zmienic na refirect
		}
		
		return "homePage";
		
	}

}
