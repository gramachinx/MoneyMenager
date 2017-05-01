package pl.gramachinx.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import pl.gramachinx.services.CheckConfig;

@Controller
public class ConfigurationUserController {
	
	@Autowired
	CheckConfig checkConf;
	
	@GetMapping("/configuration")
	public String configPage()
	{
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		if(checkConf.ifConfig(username))
		{
			return "redirect:/user";
		}
		return "configPage";
	}
	
	@PostMapping("/configuration")
	public String configPagePost()
	{
		return "redirect:/user";
	}
	
	

}
