package pl.gramachinx.controllers;

import java.sql.Timestamp;
import java.util.Date;

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
@Controller
public class MainInterfaceController {
	
	@Autowired
	private UserRepository repoUser;
	
	@GetMapping("/main")
	@Secured({"ROLE_CONFIGUSER"})
	public String mainPage(Model model, HttpServletRequest req)
	{
		//TODO dodac bledy itp.
		
		String authname = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = repoUser.findByUsername(authname);
		UserData userData = user.getUserData();
		model.addAttribute("bills", userData.getBills());
		
		
		
		return "userInterfacePage";
	}
	
	@GetMapping("/main/add/payment")
	@Secured({"ROLE_CONFIGUSER"})
	public String mainPage(Model model)
	{	
		Bill bill = new Bill();
		model.addAttribute("bill", bill);
		return "addTransPage";
	}
	
	@PostMapping("/main/add/payment")
	@Secured({"ROLE_CONFIGUSER"})
	public String mainPage(Bill bill, BindingResult res)
	{	
		String authname = SecurityContextHolder.getContext().getAuthentication().getName();
		bill.setTime(new Timestamp(new Date().getTime()));
		bill.setMoney(bill.getMoney()*(-1));
		bill.setPayBill(true);
		User user = repoUser.findByUsername(authname);
		user.getUserData().getBills().add(bill);
		repoUser.flush();
		return "redirect:/main";
	}
	
	
	
	@GetMapping("/main/add/wages")
	@Secured({"ROLE_CONFIGUSER"})
	public String mainPageWages(Model model)
	{	
		Bill bill = new Bill();
		model.addAttribute("bill", bill);
		return "addGetMoneyPage";
	}
	
	@PostMapping("/main/add/wages")
	@Secured({"ROLE_CONFIGUSER"})
	public String mainPageWages(@Valid Bill bill, BindingResult bindres)
	{	
		String authname = SecurityContextHolder.getContext().getAuthentication().getName();
		bill.setTime(new Timestamp(new Date().getTime()));
		bill.setPayBill(false);
		User user = repoUser.findByUsername(authname);
		user.getUserData().getBills().add(bill);
		repoUser.flush();
		return "redirect:/main";
	}
	
	

	

}
