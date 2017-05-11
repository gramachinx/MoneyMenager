package pl.gramachinx.controllers;

import java.util.List;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import pl.gramachinx.domains.Bill;
import pl.gramachinx.domains.FirstConfig;
import pl.gramachinx.domains.User;
import pl.gramachinx.domains.UserData;
import pl.gramachinx.repository.UserRepository;
import pl.gramachinx.services.SendMail;

@Controller
public class Home_Login_Controller {
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	UserRepository userRepo;
	
	@GetMapping("/")
	public String loginPage(HttpServletRequest request)
	{
		if(request.getUserPrincipal() == null)
		{
			return "loginPage";
		}else
		{
			return "redirect:/user";  
		}
	
	}
		
	@GetMapping("/tester")
	public String homePage2()
	{
		User user2 = new User();
		user2.setActive(true);
		user2.setConfig(true);
		user2.setEmail("nowy.lolek@gmail.com");
		user2.setEnabled(true);
		user2.setName("Jano");
		user2.setPassword(bCryptPasswordEncoder.encode("zaq12wsx"));
		user2.setRole("ROLE_CONFIGUSER");
		user2.setSpecialNumber(10000000);
		user2.setUsername("test");
		user2.setTime(new Timestamp(new Date().getTime()));
		
		UserData userData = new UserData();
		FirstConfig fc = new FirstConfig();
		fc.setFirstWalletValue(1000);
		fc.setSocialGroup("Student");
		
		List<Bill> listBill = new ArrayList<Bill>();
		Bill bill = new Bill();
		bill.setCathegory("Food");
		bill.setPayBill(true);
		bill.setMoney(-13);
		bill.setTime(new Timestamp(new Date().getTime() + 1000));
		
		Bill bill2 = new Bill();
		bill2.setCathegory("Salary");
		bill2.setPayBill(false);
		bill2.setMoney(12000);
		bill2.setTime(new Timestamp(new Date().getTime() + 1000));
		
		
		listBill.add(bill);
		listBill.add(bill2);
		userData.setBills(listBill);
		userData.setFirstConfig(fc);
		userData.setUser(user2);
		user2.setUserData(userData);
		
		userRepo.saveAndFlush(user2);
		
		
		
		return "logout";
	}

}
