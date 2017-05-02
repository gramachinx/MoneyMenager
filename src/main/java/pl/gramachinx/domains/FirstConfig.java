package pl.gramachinx.domains;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class FirstConfig {
	@Id
	@GeneratedValue
	private long id;
	
	
	@NotEmpty
	@NotNull
	private String socialGroup;
	
	private double firstWalletValue;
	
	public String getSocialGroup() {
		return socialGroup;
	}
	public void setSocialGroup(String socialGroup) {
		this.socialGroup = socialGroup;
	}
	public double getFirstWalletValue() {
		return firstWalletValue;
	}
	public void setFirstWalletValue(double firstWalletValue) {
		this.firstWalletValue = firstWalletValue;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	
	

}
