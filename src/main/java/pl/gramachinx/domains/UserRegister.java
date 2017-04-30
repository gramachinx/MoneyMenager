package pl.gramachinx.domains;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class UserRegister {
	@Id
	@GeneratedValue
	private long id;
	
	@NotEmpty
	@Size(min=3, max=25)
	private String username;
	
	@NotEmpty
	@Size(min=6)
	private String password;
	
	@NotEmpty
	private String repassword;
	
	@NotEmpty
	@Size(max=15)
	private String name;
	
	@Email
	@NotEmpty
	private String mail;
	

}
