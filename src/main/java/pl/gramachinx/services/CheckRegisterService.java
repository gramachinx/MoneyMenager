package pl.gramachinx.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.gramachinx.domains.User;
import pl.gramachinx.repository.UserRepository;

@Service
public class CheckRegisterService {

	@Autowired
	UserRepository userRepo;
	
	public boolean usernameExist(String username)
	{
		User user = userRepo.getByUsername(username);
		
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
		User user = userRepo.getByEmail(email);
		
		if(user == null)
		{
			return false;
		}else
		{
			return true;
		}
	}
}
