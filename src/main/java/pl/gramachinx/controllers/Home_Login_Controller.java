package pl.gramachinx.controllers;

import java.util.List;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import pl.gramachinx.domains.Bill;
import pl.gramachinx.domains.FirstConfig;
import pl.gramachinx.domains.User;
import pl.gramachinx.domains.UserData;
import pl.gramachinx.repository.UserRepository;
import pl.gramachinx.services.SendMail;

@Controller
public class Home_Login_Controller {
	@Autowired
	private User testUser;

	private UserRepository userRepo;
	
	protected final Logger log = Logger.getLogger(getClass().getName());

	@Autowired
	public Home_Login_Controller(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}

	@GetMapping("/")
	public String loginPage(HttpServletRequest request) {
		if (request.getUserPrincipal() == null) {
			return "loginPage";
		} else {
			return "redirect:/user";
		}

	}

	@GetMapping("/tester")
	public String homePage2() {

		userRepo.saveAndFlush(testUser);

		return "logout";
	}

}
