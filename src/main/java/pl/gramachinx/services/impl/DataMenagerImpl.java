package pl.gramachinx.services.impl;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.gramachinx.domains.Bill;
import pl.gramachinx.domains.Debt;
import pl.gramachinx.domains.User;
import pl.gramachinx.domains.UserData;
import pl.gramachinx.exceptions.ObjectNotFoundException;
import pl.gramachinx.repository.UserDataRepository;
import pl.gramachinx.repository.UserRepository;
import pl.gramachinx.services.DataInterface;
@Service
public class DataMenagerImpl implements DataInterface{
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private UserDataRepository userDataRepo;
	@Override
	public List<Bill> getSortedList(UserData userData) {
		List<Bill> lista = userData.getBills();
		lista.sort(new Comparator<Bill>() {

			@Override
			public int compare(Bill o1, Bill o2) {
				if(o1.getTime().getTime() > o2.getTime().getTime())
				{
					return -1;
				}else
				{
					return 1;
				}
			}
		});
		return lista;
	}

	@Override
	public List<Bill> getSortedListByCathegory(UserData userdata, String cathegory) {
		List<Bill> list = getSortedList(userdata);
		List<Bill> listToReurn = new ArrayList<Bill>();
		for(Bill b : list)
		{
			if(b.getCathegory().equals(cathegory))
			{
				listToReurn.add(b);
			}
		}
		return listToReurn;
	}

	@Override
	public Debt getDebtById(UserData userdata, long id) {
		List<Debt> lst = userdata.getDebt();
		Debt debt = new Debt();
		for(Debt d : lst)
		{
			if(d.getId() == id)
			{
				debt = d;
			}	
		}
		return debt;
	}
	

	@Override
	public void addUserDebt(UserData userData, Debt debt) {
	

		debt.setUserDebt(true);
		debt.setMoney(debt.getMoney()*(-1));
		userData.getDebt().add(debt);
		userRepo.flush();
		
	}

	@Override
	public void addDebt(UserData userData, Debt debt) {
		
		debt.setUserDebt(false);
		userData.getDebt().add(debt);
		userRepo.flush();
	}

	@Override
	public void addPayment(UserData userData, Bill bill) {

		bill.setTime(new Timestamp(new Date().getTime()));
		bill.setMoney(bill.getMoney()*(-1));
		bill.setPayBill(true);
		userData.getBills().add(bill);
		userData.setWallet(userData.getWallet() + bill.getMoney());
		userRepo.flush();
		
	}

	@Override
	public void addWages(UserData userData, Bill bill) {
		
		bill.setTime(new Timestamp(new Date().getTime()));	
		bill.setPayBill(false);
		userData.getBills().add(bill);
		userData.setWallet(userData.getWallet() + bill.getMoney());
		userRepo.flush();
		
	}

	@Override
	public UserData getUserData(Principal princip) {
		String userName = princip.getName();
		return userDataRepo.findByUserUsername(userName);
	}

	@Override
	public List<Debt> getDebtList(UserData userData) {
		return userData.getDebt();
	}

	@Override
	public void editDebt(Debt debtToEdit, UserData userData, double cash, Debt debt) {
		
		List<Debt> lst = userData.getDebt();
		lst.remove(debtToEdit);
		
		if(debtToEdit.isUserDebt()==true)
		{
			debtToEdit.setDeadline(debt.getDeadline());
			debtToEdit.setMoney(debtToEdit.getMoney()+cash);
			lst.add(debtToEdit);
			userData.setWallet((userData.getWallet())-cash);
		}else
		{
			debtToEdit.setDeadline(debt.getDeadline());
			debtToEdit.setMoney(debtToEdit.getMoney()-cash);
			lst.add(debtToEdit);
			userData.setWallet((userData.getWallet())+cash);
		}
		
		
		userRepo.flush();
		
	}

	@Override
	public void debtRemove(UserData userData, Debt debt) {
		userData.getDebt().remove(debt);
		userRepo.flush();
		
	}

	@Override
	public void billRemove(UserData userData, long id) throws ObjectNotFoundException {
		List<Bill> bills = userData.getBills();
		Bill bTR = null;
		for(Bill b : bills)
		{
			if(b.getId()==id)
			{
				bTR = b;
			}
		}
		
		if(bTR == null)
		{
			throw new ObjectNotFoundException("Not found bill with that id");
		}else
		{
			userData.setWallet(userData.getWallet() - bTR.getMoney());
			userData.getBills().remove(bTR);
			userDataRepo.flush();
		}
		
		
		
	}

}
