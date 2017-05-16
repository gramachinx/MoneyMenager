package pl.gramachinx.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pl.gramachinx.domains.User;
import pl.gramachinx.repository.UserRepository;

@Controller
@Secured("ROLE_CONFIGUSER")
public class DebtController {
	@Autowired
	UserRepository userRepo;
	
	@GetMapping("/main/debt")
	public String debtPage(Model model)
	{
		String authname = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepo.findByUsername(authname);	
		model.addAttribute("debets", user.getUserData().getDebt());
		return "debtPage";
	}

}
