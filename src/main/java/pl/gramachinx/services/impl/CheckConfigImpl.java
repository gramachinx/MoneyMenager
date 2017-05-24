package pl.gramachinx.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.gramachinx.domains.User;
import pl.gramachinx.repository.UserRepository;
import pl.gramachinx.services.CheckConfig;

@Service
public class CheckConfigImpl implements CheckConfig{
	@Autowired
	private UserRepository userRepo;
	
	
	@Override
	public boolean ifConfig(String username) {
		User user = userRepo.findByUsername(username);
		
		return user.isConfig();
	}

}
