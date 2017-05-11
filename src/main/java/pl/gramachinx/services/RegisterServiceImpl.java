package pl.gramachinx.services;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pl.gramachinx.domains.User;
import pl.gramachinx.domains.UserRegister;
import pl.gramachinx.repository.UserRepository;

@Service
public class RegisterServiceImpl implements RegisterService {
		@Autowired
		private UserRepository userRepo;
		
		@Autowired
		SendMail sendMail;
		
		@Autowired
	    private BCryptPasswordEncoder bCryptPasswordEncoder;
		
		public boolean addUser(UserRegister user)
		{
			User createdUser = new User();
			createdUser.setTime(new Timestamp(new Date().getTime()));
			createdUser.setActive(false);
			createdUser.setConfig(false);
			createdUser.setEmail(user.getEmail());
			createdUser.setName(user.getName());
			createdUser.setRole("USER"); //TODO sprobowac wprowadzic admina do bazdy danych i probowac dzialc
			createdUser.setUsername(user.getUsername());
			createdUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			createdUser.setSpecialNumber(((long) ((1 + Math.random())*100000000))); //TODO change it.
			createdUser.setEnabled(true);
			System.out.println(createdUser.toString());
			userRepo.save(createdUser);
			
		//	sendMail.sendMail(createdUser.getName());
			
			return false;
			
		}
}
