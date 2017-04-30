package pl.gramachinx.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import pl.gramachinx.services.CheckAuthorize;

@Controller
public class AuthController {
	@Autowired
	CheckAuthorize checkauth;
	
	@GetMapping("/authorize")
	public String authorziePage()
	{
		return "activatedPage";
	}
	
	@PostMapping("/authorize")
	public String authorziePostPage(HttpServletRequest request)
	{
		long code = Long.parseLong(request.getParameter("code"));
		if(checkauth.codeCorrect(SecurityContextHolder.getContext().getAuthentication().getName(), code))
		{
			// TODO to zmien isactived na true; make a service i w html 
		}
		
		return "activatedPage";
	}

}
