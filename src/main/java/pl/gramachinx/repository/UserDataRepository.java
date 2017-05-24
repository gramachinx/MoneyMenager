package pl.gramachinx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.gramachinx.domains.User;
import pl.gramachinx.domains.UserData;

public interface UserDataRepository extends JpaRepository<UserData, Long> {
	
	UserData findByUserUsername(String username);
	

}
