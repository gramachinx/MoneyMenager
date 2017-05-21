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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import pl.gramachinx.domains.UserRegister;
import pl.gramachinx.exceptions.EmailExistException;
import pl.gramachinx.exceptions.UserExistException;
import pl.gramachinx.services.CheckRegisterService;
import pl.gramachinx.services.RegisterService;
import pl.gramachinx.services.SendMail;
import pl.gramachinx.services.impl.CheckRegisterServiceImpl;
import pl.gramachinx.services.impl.RegisterServiceImpl;

@Controller
public class RegsiterController {

	private CheckRegisterService userServ;

	private RegisterService userRegService;
	private SendMail sendMail;

	protected final Logger log = Logger.getLogger(getClass().getName());

	@Autowired
	public RegsiterController(CheckRegisterService userServ, RegisterService userRegService, SendMail sendMail) {
		super();
		this.userServ = userServ;
		this.userRegService = userRegService;
		this.sendMail = sendMail;
	}

	@GetMapping("/register")
	public String registerPage(Model model) {
		model.addAttribute("userRegister", new UserRegister());
		return "registerPage";
	}

	@PostMapping
	public String registerPostPage(@Valid @ModelAttribute("userRgister") UserRegister userReg, BindingResult result, Model model) {
	
		if (result.hasErrors()) {
			log.error("Problem with register");
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
			ObjectError err = new ObjectError("emailExist", "Email o takiej składnij juz istnieje");
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

		
		userRegService.addUser(userReg);
		sendMail.sendMail(userReg.getUsername());
		return "redirect:/user";  
	}

}
