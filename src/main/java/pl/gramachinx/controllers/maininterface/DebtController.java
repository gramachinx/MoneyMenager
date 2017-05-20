package pl.gramachinx.controllers.maininterface;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestParam;

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
		debt.setUserDebt(true);
		debt.setMoney(debt.getMoney()*(-1));
		user.getUserData().getDebt().add(debt);
		userRepo.saveAndFlush(user);
		return "redirect:/debets/debt/user/add";
	}
	
	@GetMapping("/debets/debt/edit/{id}")
	public String editPageDebt(@PathVariable long id, Model model, Principal princip)
	{
		User user = userRepo.findByUsername(princip.getName());
		List<Debt> lst = user.getUserData().getDebt();
		Debt debtToEdit = new Debt();
		for(Debt d : lst)
		{
			if(d.getId() == id)
			{
				debtToEdit = d;
			}
		}
		model.addAttribute("debt", debtToEdit);
		return "editDebtPage";
	}
	
	@PostMapping("/debets/debt/edit/{id}")
	public String editPagePostPage(@RequestParam("cash") double cash, Debt debt, @PathVariable long id, Principal princip)
	{
		if(debt.getId() == id)
		{
			User user = userRepo.findByUsername(princip.getName());
			List<Debt> lst = user.getUserData().getDebt();
			Debt debtToEdit = new Debt();
			for(Debt d : lst)
			{
				if(d.getId() == id)
				{
					debtToEdit = d;
				}
			}
			lst.remove(debtToEdit);
			debtToEdit.setDeadline(debt.getDeadline());
			debtToEdit.setMoney(debtToEdit.getMoney()-cash);
			lst.add(debtToEdit);
			user.getUserData().setWallet((user.getUserData().getWallet())-cash);
			userRepo.flush();
			
		}
		return "redirect:/debets";
	}
	
	@GetMapping("/debets/debt/delete/{id}")
	public String deletePageDebt(@PathVariable long id, Model model, Principal princip)
	{
		User user = userRepo.findByUsername(princip.getName());
		List<Debt> lst = user.getUserData().getDebt();
		Debt debtToRemove = new Debt();
		for(Debt d : lst)
		{
			if(d.getId() == id)
			{
				debtToRemove = d;
			}
		}
		lst.remove(debtToRemove);
		userRepo.flush();
		return "redirect:/debets";
	}
}
