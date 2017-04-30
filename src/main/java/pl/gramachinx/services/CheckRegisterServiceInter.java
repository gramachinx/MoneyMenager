package pl.gramachinx.services;

import org.springframework.stereotype.Service;
// TODO naprawic blad z tymi servisami i cmponentami
@Service
public interface CheckRegisterServiceInter {
	public boolean usernameExist(String username);
	public boolean emailExist(String email);
}
