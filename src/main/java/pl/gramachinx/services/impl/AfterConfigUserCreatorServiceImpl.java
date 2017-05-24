package pl.gramachinx.services.impl;



import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.gramachinx.domains.Bill;
import pl.gramachinx.domains.FirstConfig;
import pl.gramachinx.domains.User;
import pl.gramachinx.domains.UserData;
import pl.gramachinx.repository.UserRepository;
import pl.gramachinx.services.AfterConfigUserCreatorService;
@Service
public class AfterConfigUserCreatorServiceImpl implements AfterConfigUserCreatorService {
	
	@Autowired
	private UserRepository userrepo;

	@Override
	public void fullConfigUser(FirstConfig firstConfig, String username) {
		
		User user = userrepo.findByUsername(username);

		UserData userData = new UserData();
		
		userData.setFirstConfig(firstConfig);
		List<Bill> bill = new ArrayList<Bill>();
		Bill bl = new Bill();
		bl.setPayBill(false);
		bl.setCathegory("Pierwsza kwota w portfelu.");
		bl.setMoney(firstConfig.getFirstWalletValue());
		bl.setTime(new Timestamp(new Date().getTime()));
		bill.add(bl);
		userData.setWallet(firstConfig.getFirstWalletValue());
		userData.setBills(bill);
		
		
		user.setUserData(userData);
		user.setConfig(true);
		user.setRole("ROLE_CONFIGUSER");
		userData.setUser(user);
		userrepo.save(user);
		userrepo.flush();
		
		
	}

}
