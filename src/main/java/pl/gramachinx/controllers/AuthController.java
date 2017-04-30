package pl.gramachinx.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import pl.gramachinx.services.ActivateService;
import pl.gramachinx.services.CheckAuthorize;

@Controller
public class AuthController {
	@Autowired
	CheckAuthorize checkauth;
	
	@Autowired
	ActivateService actServ;
	
	@GetMapping("/authorize")
	public String authorziePage()
	{
		return "activatedPage";
	}
	
	@PostMapping("/authorize")
	public String authorziePostPage(HttpServletRequest request)
	{
		long code = Long.parseLong(request.getParameter("activatedNumber"));
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		if(checkauth.codeCorrect(username, code))
		{
			actServ.active(username); 
			return "redirect:/user";
		}else
		{
			System.out.println("error morda");
		}
		
		return "activatedPage";
	}

}
