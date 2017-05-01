package pl.gramachinx.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.gramachinx.domains.User;
import pl.gramachinx.repository.UserRepository;

@Service
public class CheckConfigImpl implements CheckConfig{
	@Autowired
	UserRepository userRepo;
	
	
	@Override
	public boolean ifConfig(String username) {
		User user = userRepo.findByUsername(username);
		
		return user.isConfig();
	}

}