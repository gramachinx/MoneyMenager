package pl.gramachinx.controllers;

import java.security.Principal;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.gramachinx.domains.User;
import pl.gramachinx.exceptions.UserExistException;
import pl.gramachinx.repository.UserRepository;
import pl.gramachinx.services.PasswordService;

@Controller
@Secured("ROLE_CONFIGUSER")
public class PasswordChangeController {
	private UserRepository userRepo;
	private PasswordService passServ;
	public PasswordChangeController(UserRepository userRepo, PasswordService passServ) {
		this.userRepo=userRepo;
		this.passServ=passServ;
	}
	
	@GetMapping("/password/change")
	public String changePass(Model model)
	{
		return "changePassword";
	}
	
	@PostMapping("/password/change")
	public String changePostPass(@RequestParam String oldPass, @RequestParam String newPass, Principal princip,Model model) throws UserExistException
	{
		User user = userRepo.findByUsername(princip.getName());
		if(user == null) throw new UserExistException("User do not exist");
		
		if(passServ.passwordMatch(oldPass, user))
		{
			passServ.changePass(newPass, user);
			model.addAttribute("mess", "Hasło zostało zmienione.");
		}else
		{
			model.addAttribute("mess", "Stare hasło jest nie prawidłowe");
		}
		
		return "changePassword";
	}
}
