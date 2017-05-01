package pl.gramachinx.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import pl.gramachinx.services.SendMail;

@Controller
public class Home_Login_Controller {
	@Autowired
	SendMail sm;
	
	@GetMapping("/")
	public String loginPage(HttpServletRequest request)
	{
		if(request.getUserPrincipal() == null)
		{
			return "loginPage";
		}else
		{
			return "redirect:/user";  
		}
	
	}
	
	@GetMapping("/user3")
	public String homePage()
	{
		return "configPage";
	}
	
	@GetMapping("/user2")
	public String home2Page(HttpServletRequest request)
	{
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.getName());
		sm.sendMail(auth.getName());
		
		return "homePage";
	}

}
