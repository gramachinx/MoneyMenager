package pl.gramachinx.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.gramachinx.domains.User;
import pl.gramachinx.repository.UserRepository;
@Service
public class CheckAuthorizeImpl implements CheckAuthorize {

	@Autowired
	UserRepository userRepo;
	
	
	@Override
	public boolean ifAuthorized(String username) {
		User user = userRepo.findByUsername(username);
		return user.isActive();
	}

}
