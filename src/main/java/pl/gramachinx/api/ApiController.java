package pl.gramachinx.api;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.gramachinx.domains.Bill;
import pl.gramachinx.domains.Debt;
import pl.gramachinx.domains.User;
import pl.gramachinx.repository.UserRepository;
import pl.gramachinx.services.DataInterface;

@RestController
@RequestMapping("/api")
public class ApiController {

	private DataInterface dataServ;
	private UserRepository userRepo;

	@Autowired
	public ApiController(DataInterface dataServ, UserRepository userRepo) {
		this.dataServ = dataServ;
		this.userRepo = userRepo;
	}

	@RequestMapping(value = "/bills", method = RequestMethod.GET)
	public List<Bill> getBillList(Principal principal) {
		System.out.println(principal.getName());
		return dataServ.getSortedList(userRepo.findByUsername(principal.getName()).getUserData());
	}

	@RequestMapping(value = "/bills", method = RequestMethod.POST)
	public ResponseEntity<Bill> createBill(@RequestBody Bill bill, Principal principal) {

		HttpStatus status = HttpStatus.OK;
		User user = userRepo.findByUsername(principal.getName());
		List<Bill> list = user.getUserData().getBills();
		if (!list.contains(bill)) {
			list.add(bill);
			status = HttpStatus.CREATED;
			userRepo.flush();
		}

		return new ResponseEntity<>(bill, status);
	}

	@RequestMapping(value = "/debets", method = RequestMethod.GET)
	public List<Debt> getDebetList(Principal principal) {
		return dataServ.getDebtList(userRepo.findByUsername(principal.getName()).getUserData());
	}

	@RequestMapping(value = "/debets", method = RequestMethod.POST)
	public ResponseEntity<Debt> createDebet(@RequestBody Debt debt, Principal principal) {
		HttpStatus status = HttpStatus.OK;
		User user = userRepo.findByUsername(principal.getName());
		List<Debt> list = user.getUserData().getDebt();
		if (!list.contains(debt)) {
			list.add(debt);
			status = HttpStatus.CREATED;
			userRepo.flush();
		}

		return new ResponseEntity<>(debt, status);
	}

}
