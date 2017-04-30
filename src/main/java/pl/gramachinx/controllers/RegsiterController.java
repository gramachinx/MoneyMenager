package pl.gramachinx.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import pl.gramachinx.domains.UserRegister;

@Controller
public class RegsiterController {
	
	@GetMapping("/register")
	public String registerPage(Model model)
	{
		model.addAttribute("userRegister", new UserRegister());
		return "registerPage";
	}
	
	@PostMapping
	public String registerPostPage(@Valid UserRegister userReg, BindingResult result)
	{
		System.out.println(userReg.getEmail());
		
		if(result.hasErrors())
		{
			return "registerPage";
		}
		
		return "/"; // strona ktora poinformuje o strzoonym urzytkowniku
	}

}
