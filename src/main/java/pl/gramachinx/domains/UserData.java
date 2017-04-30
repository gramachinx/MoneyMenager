package pl.gramachinx.domains;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserData {
	
	@Id
	@GeneratedValue
	private long id;

}
