package pl.gramachinx.controllers.maininterface;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
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

import javassist.tools.rmi.ObjectNotFoundException;
import pl.gramachinx.domains.Debt;
import pl.gramachinx.domains.User;
import pl.gramachinx.domains.UserData;
import pl.gramachinx.repository.UserRepository;
import pl.gramachinx.services.DataInterface;

@Controller
@Secured("ROLE_CONFIGUSER")
public class DebtController {

	private UserRepository userRepo;

	private DataInterface dataServ;
	protected final Logger log = Logger.getLogger(getClass().getName());
	@Autowired
	public DebtController(UserRepository userRepo, DataInterface dataServ) {
		super();
		this.userRepo = userRepo;
		this.dataServ = dataServ;
	}

	@GetMapping("/debets")
	public String debtPage(Model model, Principal princip) {
		UserData userData = dataServ.getUserData(princip);
		model.addAttribute("debets", dataServ.getDebtList(userData));

		return "debtListPage";
	}

	@GetMapping("/debets/debt")
	public String debtPage() {
		return "debtPage";
	}

	@GetMapping("/debets/debt/add")
	public String debtAddPage(Model model) {
		model.addAttribute("debt", new Debt());
		return "debtAddPage";
	}

	@PostMapping("/debets/debt/add")
	public String debtAddPage(@Valid Debt debt, BindingResult result, Principal princip) {
		UserData userData = dataServ.getUserData(princip);
		dataServ.addDebt(userData, debt);

		return "redirect:/debets/debt/add";
	}

	@GetMapping("/debets/debt/user/add")
	public String debtAddPage2(Model model) {
		model.addAttribute("debt", new Debt());
		return "userDebtAddPage";
	}

	@PostMapping("/debets/debt/user/add")
	public String debtAddPage2(@Valid Debt debt, BindingResult result, Principal princip) {
		UserData userData = dataServ.getUserData(princip);
		dataServ.addUserDebt(userData, debt);

		return "redirect:/debets/debt/user/add";
	}

	@GetMapping("/debets/debt/edit/{id}")
	public String editPageDebt(@PathVariable long id, Model model, Principal princip) throws ObjectNotFoundException {
		UserData userData = dataServ.getUserData(princip);
		Debt debt = dataServ.getDebtById(userData, id);
		if(debt==null)
		{
			log.error("There is not object with that id");
			throw new ObjectNotFoundException("There is not object with that id");
			
		}
		model.addAttribute("debt", debt);
		return "editDebtPage";
	}

	@PostMapping("/debets/debt/edit/{id}")
	public String editPagePostPage(@RequestParam("cash") double cash, Debt debt, @PathVariable long id,
			Principal princip) {
		if (debt.getId() == id) {
			UserData userData = dataServ.getUserData(princip);
			Debt debtToEdit = dataServ.getDebtById(userData, id);
			dataServ.editDebt(debtToEdit, userData, cash, debt);

		}
		return "redirect:/debets";
	}

	@GetMapping("/debets/debt/delete/{id}")
	public String deletePageDebt(@PathVariable long id, Principal princip) throws ObjectNotFoundException {
		UserData userData = dataServ.getUserData(princip);
		Debt debtToRemove = dataServ.getDebtById(userData, id);
		
		if(debtToRemove == null)
		{
			log.error("There is not object with that id");
			throw new ObjectNotFoundException("There is not object with that id");
		}
		dataServ.debtRemove(userData, debtToRemove);
		return "redirect:/debets";
	}
}
