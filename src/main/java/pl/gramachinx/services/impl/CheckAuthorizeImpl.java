package pl.gramachinx.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.gramachinx.domains.User;
import pl.gramachinx.repository.UserRepository;
import pl.gramachinx.services.CheckAuthorize;
@Service
public class CheckAuthorizeImpl implements CheckAuthorize {

	@Autowired
	UserRepository userRepo;
	
	
	@Override
	public boolean ifAuthorized(String username) {
		User user = userRepo.findByUsername(username);
		return user.isActive();
	}


	@Override
	public boolean codeCorrect(String username, String code) {
		User user = userRepo.findByUsername(username);
		
		return user.getSpecialNumber().equals(code);
	}

}
