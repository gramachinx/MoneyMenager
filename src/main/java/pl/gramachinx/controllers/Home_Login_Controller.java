package pl.gramachinx.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Home_Login_Controller {
	
	@GetMapping("/")
	public String loginPage()
	{
		return "loginPage";
	}
	
	@GetMapping("/user")
	public String homePage()
	{
		return "homePage";
	}

}
