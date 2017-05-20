package pl.gramachinx.domains;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;


@Entity

public class Bill {
	@Id
	@GeneratedValue
	private long id;
	private Timestamp time;
	@NotBlank
	private String cathegory;
	private double money;
	@NotNull
	private boolean isPayBill;
	


	
	
	public boolean isPayBill() {
		return isPayBill;
	}
	public void setPayBill(boolean isPayBill) {
		this.isPayBill = isPayBill;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public String getCathegory() {
		return cathegory;
	}
	public void setCathegory(String cathegory) {
		this.cathegory = cathegory;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
	
		this.money = money;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	

}
