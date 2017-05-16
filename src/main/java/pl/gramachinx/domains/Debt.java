package pl.gramachinx.domains;

import java.util.GregorianCalendar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@Entity
public class Debt {				//TODO zrobic z tego dzidziczenie
								//TODO w html stworzyc dwie listy dluznikow i dlugow
	@Id
	@GeneratedValue
	private long id;
	@NotNull
	private String creditor;
	@NotNull
	private double money;
	private GregorianCalendar deadline;
	private boolean isPaid;
	@NotNull
	private boolean isUserDebt;
	
	
	

	
	public boolean isUserDebt() {
		return isUserDebt;
	}

	public void setUserDebt(boolean isUserDebt) {
		this.isUserDebt = isUserDebt;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public String getCreditor() {
		return creditor;
	}
	public void setCreditor(String creditor) {
		this.creditor = creditor;
	}
	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public GregorianCalendar getDeadline() {
		return deadline;
	}

	public void setDeadline(GregorianCalendar deadline) {
		this.deadline = deadline;
	}
	
	
	

}
