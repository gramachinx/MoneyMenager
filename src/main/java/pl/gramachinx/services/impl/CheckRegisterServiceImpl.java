package pl.gramachinx.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.gramachinx.domains.User;
import pl.gramachinx.repository.UserRepository;
import pl.gramachinx.services.CheckRegisterService;

@Service
public class CheckRegisterServiceImpl implements CheckRegisterService{

	@Autowired
	UserRepository userRepo;
	
	public boolean usernameExist(String username)
	{
		User user = userRepo.findByUsername(username);

		if(user == null)
		{
			return false;
		}else
		{
			return true;
		}
	}
	
	public boolean emailExist(String email)
	{
		User user = userRepo.findByEmail(email);
		
		if(user == null)
		{
			return false;
		}else
		{
			return true;
		}
	}
}
