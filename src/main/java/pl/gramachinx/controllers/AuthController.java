package pl.gramachinx.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
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

	private ActivateService actServ;

	private SendMail sm;

	private CheckAuthorize checkauth;
	
	protected final Logger log = Logger.getLogger(getClass().getName());
	
	@Autowired
	public AuthController(CheckAuthorize checkauth, ActivateService actServ, SendMail sm) {
		super();
		this.checkauth = checkauth;
		this.actServ = actServ;
		this.sm = sm;
	}

	@GetMapping("/authorize")
	public String authorziePage(Principal princip) {
		String authname = princip.getName();

		if (!checkauth.ifAuthorized(SecurityContextHolder.getContext().getAuthentication().getName())) {
			sm.sendMail(authname);
			log.info("Send email to: " + authname);
			return "activatedPage";
		} else {
			return "redirect:/user";
		}
	}

	@PostMapping("/authorize")
	public String authorziePostPage(HttpServletRequest request, Principal princip) // BindException
																					// class
																					// ??
																					// do
																					// errorow
	{
		if (!checkauth.ifAuthorized(SecurityContextHolder.getContext().getAuthentication().getName())) {
			long code = Long.parseLong(request.getParameter("activatedNumber"));
			String username = princip.getName();
			if (checkauth.codeCorrect(username, code)) {
				actServ.active(username);
				return "redirect:/user";
			} else {
				log.error(username + "has inserted incorect acvtivate code.");
			}
		} else {
			return "redirect:/user";
		}
		return "activatedPage";
	}

}
