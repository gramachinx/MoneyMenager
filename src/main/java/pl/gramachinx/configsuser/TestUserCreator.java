package pl.gramachinx.configsuser;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import pl.gramachinx.domains.Bill;
import pl.gramachinx.domains.Debt;
import pl.gramachinx.domains.FirstConfig;
import pl.gramachinx.domains.User;

import pl.gramachinx.domains.UserData;


@Configuration
public class TestUserCreator {
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Bean
	@Qualifier("UserAuth")
	public User testUserAuth()
	{
		User user2 = new User();
		user2.setActive(true);
		user2.setConfig(true);
		user2.setEmail("nowy.lolek@gmail.com");
		user2.setEnabled(true);
		user2.setName("Jano");
		user2.setPassword(bCryptPasswordEncoder.encode("test2"));
		user2.setRole("ROLE_CONFIGUSER");
		user2.setSpecialNumber(UUID.randomUUID().toString());
		user2.setUsername("test");
		user2.setTime(new Timestamp(new Date().getTime()));
		
		UserData userData = new UserData();
		FirstConfig fc = new FirstConfig();
		fc.setFirstWalletValue(11987);
		fc.setSocialGroup("Student");
		
		List<Bill> listBill = new ArrayList<Bill>();
		Bill bill = new Bill();
		bill.setPayBill(true);
		bill.setCathegory("Food");
		bill.setMoney(-13);
		bill.setTime(new Timestamp(new Date().getTime() + 1000));
		
		Bill bill2 = new Bill();
		bill2.setPayBill(false);
		bill2.setCathegory("Salary");
		bill2.setMoney(12000);
		bill2.setTime(new Timestamp(new Date().getTime() + 1000));
		
		List<Debt>  debtList = new ArrayList<>();
		Debt debt1 = new Debt();
		debt1.setUserDebt(false);
		debt1.setCreditor("Wiesiek");
		debt1.setMoney(1000);
		//debt1.setDeadline(new GregorianCalendar());
		debtList.add(debt1);
		
		Debt debt2 = new Debt();
		debt2.setUserDebt(true);
		debt2.setCreditor("My.");
		debt2.setMoney(100);
		//debt2.setDeadline(new GregorianCalendar());
		debtList.add(debt2);
		
		listBill.add(bill);
		listBill.add(bill2);
		userData.setDebt(debtList);;
		userData.setBills(listBill);
		userData.setFirstConfig(fc);
		userData.setUser(user2);
		userData.setWallet(fc.getFirstWalletValue());
		user2.setUserData(userData);
		
		return user2;
	}
	
	@Bean(name="UserNoAuth")
	@Qualifier("UserNoAuth")
	public User testUserNoAuth()
	{
		User user2 = new User();
		user2.setActive(false);
		user2.setConfig(false);
		user2.setEmail("nowy.lolerk@gmail.com");
		user2.setEnabled(false);
		user2.setName("JanoPW");
		user2.setPassword(bCryptPasswordEncoder.encode("test2"));
		user2.setRole("ROLE_USER");
		user2.setSpecialNumber(UUID.randomUUID().toString());
		user2.setUsername("testNoAuth");
		user2.setTime(new Timestamp(new Date().getTime()));
		
		return user2;
	}

}
