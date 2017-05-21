package pl.gramachinx.services.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pl.gramachinx.domains.User;
import pl.gramachinx.domains.UserRegister;
import pl.gramachinx.repository.UserRepository;
import pl.gramachinx.services.RegisterService;
import pl.gramachinx.services.SendMail;

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
			createdUser.setRole("USER"); 
			createdUser.setUsername(user.getUsername());
			createdUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			createdUser.setSpecialNumber(UUID.randomUUID().toString());
			createdUser.setEnabled(true);
			userRepo.saveAndFlush(createdUser);
			return true;
			
		}
}
