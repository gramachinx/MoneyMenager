package pl.gramachinx.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import pl.gramachinx.domains.Debt;
import pl.gramachinx.domains.User;
import pl.gramachinx.repository.UserRepository;

@Controller
@Secured("ROLE_CONFIGUSER")
public class DebtController {
	@Autowired
	UserRepository userRepo;
	
	
	@GetMapping("/debets")   //TODO poprawic wszystkie linki oraz zrobic glowny wyglad strony (stopka, logo, itp.)
	public String debtPage(Model model, Principal princip)
	{
		String authname = princip.getName();
		User user = userRepo.findByUsername(authname);	
		model.addAttribute("debets", user.getUserData().getDebt());
		return "debtListPage";
	}
	
	@GetMapping("/debets/debt")
	public String debtPage()
	{
		return "debtPage";
	}
	
	
	@GetMapping("/debets/debt/add")
	public String debtAddPage(Model model)
	{
		model.addAttribute("debt", new Debt());
		return "debtAddPage";
	}
	
	@PostMapping("/debets/debt/add")
	public String debtAddPage(@Valid Debt debt, BindingResult result, Principal princip)
	{
		String username = princip.getName();
		User user = userRepo.findByUsername(username);
		debt.setUserDebt(false);
		user.getUserData().getDebt().add(debt);
		userRepo.saveAndFlush(user);
		return "redirect:/debets/debt/add";
	}
	
	
	@GetMapping("/debets/debt/user/add")
	public String debtAddPage2(Model model)
	{
		model.addAttribute("debt", new Debt());
		return "userDebtAddPage";
	}
	
	@PostMapping("/debets/debt/user/add")
	public String debtAddPage2(@Valid Debt debt, BindingResult result, Principal princip)
	{
		String username = princip.getName();
		User user = userRepo.findByUsername(username);
		debt.setCreditor("It is your debt(" + username + ")");
		debt.setUserDebt(true);
		debt.setMoney(debt.getMoney()*(-1));
		user.getUserData().getDebt().add(debt);
		userRepo.saveAndFlush(user);
		return "redirect:/debets/debt/user/add";
	}
	
	@GetMapping("/debets/debt/edit/{id}")
	public String editPageDebt(@PathVariable long id, Model model)
	{
		return "editDebtPage";
	}
	
	@GetMapping("/debets/debt/delete/{id}")
	public String deletePageDebt(@PathVariable long id, Model model)
	{
		//TODO dsd
		return "editDebtPage";
	}
}
