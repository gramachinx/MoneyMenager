package pl.gramachinx.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import pl.gramachinx.domains.FirstConfig;
import pl.gramachinx.services.AfterConfigUserCreatorService;
import pl.gramachinx.services.CheckConfig;

@Controller
public class ConfigurationUserController {
	
	@Autowired
	private CheckConfig checkConf;
	
	@Autowired
	private AfterConfigUserCreatorService configServ;
	
	private String username;
	
	@GetMapping("/configuration")
	public String configPage(Model model, Principal princip)
	{
		username = princip.getName();
		if(checkConf.ifConfig(username))
		{
			return "redirect:/user";
		}
		
		model.addAttribute("userConfig", new FirstConfig());
		return "configPage";
	}
	
	@PostMapping("/configuration")
	public String configPagePost(FirstConfig firstConf, Principal princip)
	{
		username = princip.getName();
		configServ.fullConfigUser(firstConf, username);
		
		//TODO przeladowac authtoken aby zaladowaly sie nowe role.
		
		return "redirect:/user";
	}
	
	

}
