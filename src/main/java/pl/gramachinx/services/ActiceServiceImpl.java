package pl.gramachinx.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.gramachinx.domains.User;
import pl.gramachinx.repository.UserRepository;
@Service
public class ActiceServiceImpl implements ActivateService{
	
	@Autowired
	UserRepository userRepo;
	@Override
	public void active(String user2) {
		User user = userRepo.findByUsername(user2);
		user.setActive(true);
		userRepo.flush();
	}

	@Override
	public void activeconfig(String user2) {
		User user = userRepo.findByUsername(user2);
		user.setConfig(true);
		userRepo.flush();
		
	}

}
