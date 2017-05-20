package pl.gramachinx.controllers;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import pl.gramachinx.domains.UserRegister;
import pl.gramachinx.exceptions.EmailExistException;
import pl.gramachinx.exceptions.UserExistException;
import pl.gramachinx.services.CheckRegisterService;
import pl.gramachinx.services.RegisterService;
import pl.gramachinx.services.impl.CheckRegisterServiceImpl;
import pl.gramachinx.services.impl.RegisterServiceImpl;

@Controller
public class RegsiterController {

	private CheckRegisterService userServ;

	private RegisterService userRegService;

	protected final Logger log = Logger.getLogger(getClass().getName());

	@Autowired
	public RegsiterController(CheckRegisterService userServ, RegisterService userRegService) {
		super();
		this.userServ = userServ;
		this.userRegService = userRegService;
	}

	@GetMapping("/register")
	public String registerPage(Model model) {
		model.addAttribute("userRegister", new UserRegister());
		return "registerPage";
	}

	@PostMapping
	public String registerPostPage(@Valid UserRegister userReg, BindingResult result) {
		System.out.println(userReg.getEmail());

		if (result.hasErrors()) {
			return "registerPage";
		}
		try {
			userServ.usernameExist(userReg.getUsername());
		} catch (UserExistException excep) {
			ObjectError err = new ObjectError("usernameExist", "Uzytkownik o takiej nazwie juz istnieje");
			result.addError(err);
			log.error("User with that username exist.");
			return "registerPage";
		}

		try {
			userServ.emailExist(userReg.getEmail());
		} catch (EmailExistException except) {
			ObjectError err = new ObjectError("emailExist", "Email o takiej skladnij juz istnieje");
			result.addError(err);
			log.error("User with that email exist.");
			return "registerPage";
		}

		if (!userReg.getPassword().equals(userReg.getRepassword())) {
			ObjectError err = new ObjectError("failPasswordMatch", "Hasla ze soba sa niezgodne.");
			result.addError(err);
			log.error("Password do not match.");
			return "registerPage";
		}

		// TODO wysylac maila z aktywacja i przekierowanie do strony z aktywacja

		userRegService.addUser(userReg);
		return "loginPage"; // strona ktora poinformuje o strzoonym urzytkowniku
	}

}
