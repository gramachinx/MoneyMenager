package pl.gramachinx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.gramachinx.domains.Bill;
import pl.gramachinx.domains.User;

public interface UserRepository extends JpaRepository<User, Long> {
		User findByUsername(String username);
		User findByEmail(String email);

}
