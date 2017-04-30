package pl.gramachinx.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
	@Secured(value="ADMIN")
	public String homePage()
	{
		return "homePage";
	}
	
	@GetMapping("/user2")
	public String home2Page(HttpServletRequest request)
	{
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.getName());
		
		return "homePage";
	}

}
