package pl.gramachinx.controllers.maininterface;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import pl.gramachinx.domains.Bill;
import pl.gramachinx.domains.User;
import pl.gramachinx.domains.UserData;

import pl.gramachinx.repository.UserRepository;
import pl.gramachinx.services.DataInterface;
import pl.gramachinx.services.StatisticService;

@Controller
@Secured({ "ROLE_CONFIGUSER" })
public class MainInterfaceController {

	private DataInterface dataServ;

	@Autowired
	public MainInterfaceController(DataInterface dataServ) {
		super();
		this.dataServ = dataServ;
	}

	@GetMapping("/bills")
	public String mainPage(Model model, HttpServletRequest req, Principal princip) {

		UserData userData = dataServ.getUserData(princip);
		model.addAttribute("bills", dataServ.getSortedList(userData));

		return "userInterfacePage";
	}

	@GetMapping("/bills/bill")
	public String billPage() {
		return "billPage";
	}

	@GetMapping("/bills/bill/payment/add")
	public String mainPage(Model model) {
		Bill bill = new Bill();
		model.addAttribute("bill", bill);
		return "paymentAddPage";
	}

	@PostMapping("/bills/bill/payment/add")
	public String mainPage(Bill bill, BindingResult res, Principal princip) {
		UserData userData = dataServ.getUserData(princip);
		dataServ.addPayment(userData, bill);
		return "redirect:/bills/bill/payment/add";

	}

	@GetMapping("/bills/bill/wages/add")
	public String mainPageWages(Model model) {
		Bill bill = new Bill();
		model.addAttribute("bill", bill);
		return "wagesAddPage";
	}

	@PostMapping("/bills/bill/wages/add")
	public String mainPageWages(@Valid @ModelAttribute("bill") Bill bill, BindingResult bindres, Principal princip) {
		UserData userData = dataServ.getUserData(princip);
		dataServ.addWages(userData, bill);

		return "redirect:/bills/bill/wages/add";
	}

}
