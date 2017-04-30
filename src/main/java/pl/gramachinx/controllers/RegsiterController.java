package pl.gramachinx.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import pl.gramachinx.domains.UserRegister;
import pl.gramachinx.services.CheckRegisterService;
import pl.gramachinx.services.RegisterService;

@Controller
public class RegsiterController {
	
	@Autowired
	CheckRegisterService userServ;
	
	@Autowired
	RegisterService userRegService;
	
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
		
		if(userServ.emailExist(userReg.getEmail()))
		{
			ObjectError err = new ObjectError("emailExist", "Email o takiej skladnij juz istnieje");
			result.addError(err);
			return "registerPage";
		}
		
		if(userReg.getPassword() != userReg.getRepassword())
		{
			ObjectError err = new ObjectError("failPasswordMatch", "Hasla ze soba sa niezgodne.");
			result.addError(err);
			return "registerPage";
		}
		
		
		
		userRegService.addUser(userReg);
		return "/"; // strona ktora poinformuje o strzoonym urzytkowniku
	}

}
