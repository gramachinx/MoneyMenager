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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.gramachinx.domains.Bill;
import pl.gramachinx.domains.FirstConfig;
import pl.gramachinx.domains.User;
import pl.gramachinx.domains.UserData;
import pl.gramachinx.exceptions.UserExistException;
import pl.gramachinx.repository.UserRepository;
import pl.gramachinx.services.SendMail;

@Controller
public class Home_Login_Controller {
	@Autowired
	private User testUser;

	private UserRepository userRepo;
	private SendMail mailServ;
	
	protected final Logger log = Logger.getLogger(getClass().getName());

	@Autowired
	public Home_Login_Controller(UserRepository userRepo, SendMail mailServ) {
		super();
		this.userRepo = userRepo;
		this.mailServ = mailServ;
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
	
	@GetMapping("/password/rec")
	public String homePage2(Model model) {
		
		return "passwordRePage";
	}
	
	@PostMapping("/password/rec")
	public String passwordRE(@RequestParam String email) throws UserExistException 
	{
		System.out.println(email);
		User user = userRepo.findByEmail(email);
		
		if(user != null){
		mailServ.passRecorvery(email, user);
		}else
		{
			System.out.println("email hhh");
			log.error("There is no user with that e-mail adress.");
			throw new UserExistException("There is no user with that e-mail adress.");
		}
		return "redirect:/";
	}

}
