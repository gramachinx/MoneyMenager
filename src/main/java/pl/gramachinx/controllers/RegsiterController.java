package pl.gramachinx.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegsiterController {
	
	@GetMapping("/register")
	public String registerPage()
	{
		return "registerPage";
	}

}
