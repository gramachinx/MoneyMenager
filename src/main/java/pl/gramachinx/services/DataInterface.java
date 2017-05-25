package pl.gramachinx.services;

import java.security.Principal;
import java.util.List;

import pl.gramachinx.domains.Bill;
import pl.gramachinx.domains.Debt;
import pl.gramachinx.domains.UserData;
import pl.gramachinx.exceptions.ObjectNotFoundException;

public interface DataInterface {
	
	List<Bill> getSortedList(UserData userdata);
	List<Bill> getSortedListByCathegory(UserData userdata, String cathegory);
	Debt getDebtById(UserData userdata, long id);
	void addUserDebt(UserData userData, Debt debt);
	void addDebt(UserData userData, Debt debt);
	void addPayment(UserData userData, Bill bill);
	void addWages(UserData userData, Bill bill);
	UserData getUserData(Principal princip);
	List<Debt> getDebtList(UserData userData);
	void editDebt(Debt debtToEdit, UserData userData, double cash, Debt debt);
	void debtRemove(UserData userData, Debt debt);
	void billRemove(UserData userData, long id) throws ObjectNotFoundException;

}
