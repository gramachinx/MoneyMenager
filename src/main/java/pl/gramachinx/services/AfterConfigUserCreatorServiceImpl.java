package pl.gramachinx.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.gramachinx.domains.FirstConfig;
import pl.gramachinx.domains.User;
import pl.gramachinx.domains.UserData;
import pl.gramachinx.repository.UserRepository;
@Service
public class AfterConfigUserCreatorServiceImpl implements AfterConfigUserCreatorService {
	
	@Autowired
	UserRepository userrepo;

	@Override
	public void fullConfigUser(FirstConfig firstConfig, String username) {
		
		User user = userrepo.findByUsername(username);
		
		UserData userData = new UserData();
		
		userData.setFirstConfig(firstConfig);
		
		user.setUserData(userData);
		
		user.setConfig(true);
		
		userData.setUser(user);
		userrepo.save(user);
		userrepo.flush();
		
		
	}

}
