package pl.gramachinx.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import pl.gramachinx.services.CheckAuthorize;
import pl.gramachinx.services.CheckConfig;

@Controller
public class UserController {
	
	@Autowired
	private CheckAuthorize checkauth;
	@Autowired
	private CheckConfig checkconf;
	
	
	
	
	@GetMapping("/user")
	public String userPage(Principal princip)
	{
		String username = princip.getName();
		if(!checkauth.ifAuthorized(username))
		{
		return "redirect:/authorize";	
		}
		if(!checkconf.ifConfig(username))
		{
			return "redirect:/configuration";
		}
		
		
		return "redirect:/bills";
		
	}
	
	@GetMapping("/testrest")
	public String testMeth()
	{
		return "activatedPage";
	}

}
