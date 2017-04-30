package pl.gramachinx.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pl.gramachinx.domains.User;
import pl.gramachinx.domains.UserRegister;
import pl.gramachinx.repository.UserRepository;

@Service
public class RegisterServiceImpl {
		@Autowired
		private UserRepository userRepo;
		
		@Autowired
	    private BCryptPasswordEncoder bCryptPasswordEncoder;
		
		public boolean addUser(UserRegister user)
		{
			User createdUser = new User();
			createdUser.setActive(false);
			createdUser.setConfig(false);
			createdUser.setEmail(user.getEmail());
			createdUser.setName(user.getName());
			createdUser.setRole("USER");
			createdUser.setUsername(user.getUsername());
			createdUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			createdUser.setSpecialNumber(((long) ((1 + Math.random())*10000000)));
			createdUser.setEnabled(true);
			System.out.println(createdUser.toString());
			userRepo.save(createdUser);
			
			return false;
			
		}
}