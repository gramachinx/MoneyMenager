package pl.gramachinx.services;

import org.springframework.stereotype.Service;

import pl.gramachinx.exceptions.EmailExistException;
import pl.gramachinx.exceptions.UserExistException;
// TODO naprawic blad z tymi servisami i cmponentami
@Service
public interface CheckRegisterService {
	public boolean usernameExist(String username) throws UserExistException;
	public boolean emailExist(String email) throws EmailExistException ;
}
