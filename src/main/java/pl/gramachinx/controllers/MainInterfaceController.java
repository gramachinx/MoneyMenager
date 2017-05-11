package pl.gramachinx.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;

import pl.gramachinx.domains.User;
import pl.gramachinx.domains.UserData;
import pl.gramachinx.repository.UserRepository;
@Controller

public class MainInterfaceController {
	
	@Autowired
	private UserRepository repoUser;
	
	@GetMapping("/main")
//	@Secured({"ROLE_CONFIGUSER"})
	public String mainPage(Model model, HttpServletRequest req)
	{
		
		
		String authname = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = repoUser.findByUsername(authname);
		UserData userData = user.getUserData();
		model.addAttribute("bills", userData.getBills());
		
		
		
		return "userInterfacePage";
	}
	
	

}
