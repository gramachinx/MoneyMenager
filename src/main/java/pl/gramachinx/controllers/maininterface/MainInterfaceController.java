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
import org.springframework.web.bind.annotation.PostMapping;

import pl.gramachinx.domains.Bill;
import pl.gramachinx.domains.User;
import pl.gramachinx.domains.UserData;

import pl.gramachinx.repository.UserRepository;
import pl.gramachinx.services.StatisticService;

@Controller
@Secured({"ROLE_CONFIGUSER"})
public class MainInterfaceController {
	
	@Autowired
	private UserRepository repoUser;
	@Autowired
	private StatisticService statServ;
	

	@GetMapping("/bills")
	public String mainPage(Model model, HttpServletRequest req, Principal princip)
	{
		//TODO dodac bledy itp.
		
		String authname = princip.getName();
		User user = repoUser.findByUsername(authname);
		UserData userData = user.getUserData();
		List<Bill> lista = userData.getBills();
		lista.sort(new Comparator<Bill>() {

			@Override
			public int compare(Bill o1, Bill o2) {
				if(o1.getTime().getTime() > o2.getTime().getTime())
				{
					return -1;
				}else
				{
					return 1;
				}
			}
		});
		model.addAttribute("bills", lista);
	//	model.addAttribute("saldo", statServ.getSaldo(userData));
		//model.addAttribute("userDebets", statServ.getMyDebts(userData));
		return "userInterfacePage";
	}
	
	@GetMapping("/bills/bill")
	public String billPage()
	{
		return "billPage";
	}
	
	
	@GetMapping("/bills/bill/payment/add")
	public String mainPage(Model model)
	{	
		Bill bill = new Bill();
		model.addAttribute("bill", bill);
		return "paymentAddPage";
	}
	
	@PostMapping("/bills/bill/payment/add")
	public String mainPage(Bill bill, BindingResult res, Principal princip)
	{	
		String authname = princip.getName();
		bill.setTime(new Timestamp(new Date().getTime()));
		bill.setMoney(bill.getMoney()*(-1));
		bill.setPayBill(true);
		User user = repoUser.findByUsername(authname);
		UserData userData = user.getUserData();
		userData.getBills().add(bill);
		userData.setWallet(userData.getWallet() + bill.getMoney());
		repoUser.flush();
		return "redirect:/bills/bill/payment/add";
	}
	
	
	
	@GetMapping("/bills/bill/wages/add")
	public String mainPageWages(Model model)
	{	
		Bill bill = new Bill();
		model.addAttribute("bill", bill);
		return "wagesAddPage";
	}
	
	@PostMapping("/bills/bill/wages/add")
	public String mainPageWages(@Valid Bill bill, BindingResult bindres, Principal princip)
	{	
		String authname = princip.getName();
		bill.setTime(new Timestamp(new Date().getTime()));	//TODO oddelegowac do servisu i dodac zmienianie sie poertfelaa
		bill.setPayBill(false);
		User user = repoUser.findByUsername(authname);
		UserData userData = user.getUserData();
		userData.getBills().add(bill);
		userData.setWallet(userData.getWallet() + bill.getMoney());
		repoUser.flush();
		return "redirect:/bills/bill/wages/add";
	}
	
	

	

}
