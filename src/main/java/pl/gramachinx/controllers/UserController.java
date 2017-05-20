package pl.gramachinx.controllers;

import java.security.Principal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import pl.gramachinx.services.CheckAuthorize;
import pl.gramachinx.services.CheckConfig;

@Controller
public class UserController {

	private CheckAuthorize checkauth;

	private CheckConfig checkconf;
	protected final Logger log = Logger.getLogger(getClass().getName());

	@Autowired
	public UserController(CheckAuthorize checkauth, CheckConfig checkconf) {
		super();
		this.checkauth = checkauth;
		this.checkconf = checkconf;
	}

	@GetMapping("/user")
	public String userPage(Principal princip) {
		String username = princip.getName();
		if (!checkauth.ifAuthorized(username)) {
			log.info("User " + username + " is not authorize");
			return "redirect:/authorize";
		}
		if (!checkconf.ifConfig(username)) {
			log.info("User " + username + " is not config");
			return "redirect:/configuration";
		}

		return "redirect:/bills";

	}

	@GetMapping("/testrest")
	public String testMeth() {
		return "activatedPage";
	}

}
