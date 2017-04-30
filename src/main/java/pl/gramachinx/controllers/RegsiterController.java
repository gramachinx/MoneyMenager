package pl.gramachinx.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import pl.gramachinx.domains.UserRegister;
import pl.gramachinx.services.CheckRegisterServiceImpl;
import pl.gramachinx.services.CheckRegisterServiceInter;
import pl.gramachinx.services.RegisterService;
import pl.gramachinx.services.RegisterServiceImpl;

@Controller
public class RegsiterController {
	
	@Autowired
	CheckRegisterServiceImpl userServ;
	
	@Autowired
	RegisterServiceImpl userRegService;
	
	@GetMapping("/register")
	public String registerPage(Model model)
	{
		model.addAttribute("userRegister", new UserRegister());
		return "registerPage";
	}
	
	@PostMapping
	public String registerPostPage(@Valid UserRegister userReg, BindingResult result)
	{
		System.out.println(userReg.getEmail());
		
		if(result.hasErrors())
		{
			return "registerPage";
		}
		
		if(userServ.usernameExist(userReg.getUsername()))
		{
			ObjectError err = new ObjectError("usernameExist", "Uzytkownik o takiej nazwie juz istnieje");
			result.addError(err);
			return "registerPage";
		}
		// TODO wpakowac te ify do serwisu
		if(userServ.emailExist(userReg.getEmail()))
		{
			ObjectError err = new ObjectError("emailExist", "Email o takiej skladnij juz istnieje");
			result.addError(err);
			return "registerPage";
		}
	
		if(!userReg.getPassword().equals(userReg.getRepassword()))
		{
			ObjectError err = new ObjectError("failPasswordMatch", "Hasla ze soba sa niezgodne.");
			result.addError(err);
			return "registerPage";
		}
		
		//TODO wysylac maila z aktywacja i przekierowanie do strony z aktywacja
		
		userRegService.addUser(userReg);
		return "loginPage"; // strona ktora poinformuje o strzoonym urzytkowniku
	}

}
