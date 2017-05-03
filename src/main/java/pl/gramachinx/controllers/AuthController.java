package pl.gramachinx.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import pl.gramachinx.services.ActivateService;
import pl.gramachinx.services.CheckAuthorize;
import pl.gramachinx.services.SendMail;

@Controller
public class AuthController {
	@Autowired
	private CheckAuthorize checkauth;
	
	@Autowired
	private ActivateService actServ;
	
	@Autowired
	private SendMail sm;
	
	@GetMapping("/authorize")
	public String authorziePage()
	{
		String authname = SecurityContextHolder.getContext().getAuthentication().getName();
		
		if(!checkauth.ifAuthorized(SecurityContextHolder.getContext().getAuthentication().getName()))
		{
			sm.sendMail(authname);
			return "activatedPage";
		}else
		{
			return "redirect:/user";
		}
	}
	
	@PostMapping("/authorize")
	public String authorziePostPage(HttpServletRequest request) //BindException class ?? do errorow
	{
		if(!checkauth.ifAuthorized(SecurityContextHolder.getContext().getAuthentication().getName()))
		{
		long code = Long.parseLong(request.getParameter("activatedNumber"));
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		if(checkauth.codeCorrect(username, code))
		{
			actServ.active(username); 
			return "redirect:/user";
		}else
		{
			System.out.println("err");
		}
		}else
		{
			return "redirect:/user";
		}
		return "activatedPage";
	}

}
