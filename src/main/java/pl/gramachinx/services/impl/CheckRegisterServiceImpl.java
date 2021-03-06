package pl.gramachinx.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.gramachinx.domains.User;
import pl.gramachinx.exceptions.EmailExistException;
import pl.gramachinx.exceptions.UserExistException;
import pl.gramachinx.repository.UserRepository;
import pl.gramachinx.services.CheckRegisterService;

@Service
public class CheckRegisterServiceImpl implements CheckRegisterService{

	@Autowired
	private UserRepository userRepo;
	
	public boolean usernameExist(String username) throws UserExistException
	{
		User user = userRepo.findByUsername(username);

		if(user == null)
		{
			 return false;
		}else
		{
			throw new UserExistException("Do not find user with that username");
		}
	}
	
	public boolean emailExist(String email) throws EmailExistException
	{
		User user = userRepo.findByEmail(email);
		
		if(user == null)
		{
			return false;
		}else
		{
			throw new EmailExistException("This email alredy exist");
		}
	}
}
