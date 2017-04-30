package pl.gramachinx.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.gramachinx.repository.UserRepository;

@Service
public class RegisterService {
		@Autowired
		UserRepository userRepo;
}
