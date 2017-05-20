package pl.gramachinx.controllers.maininterface;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import pl.gramachinx.domains.Bill;
import pl.gramachinx.domains.User;
import pl.gramachinx.repository.UserRepository;

@Controller
@Secured("ROLE_CONFIGUSER")
public class CathegoryController {

	private UserRepository userRepo;
	
	
	@Autowired
	public CathegoryController(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}

	@GetMapping("/debets/cathegory")
	public String catPage()
	{
		return "cathegoryPage";
	}
	
	@GetMapping("/debets/cathegory/{name}")
	public String catPage2(@PathVariable String name, Model model, Principal principal)
	{
		User user = userRepo.findByUsername(principal.getName());
		List<Bill> bills = user.getUserData().getBills();
		List<Bill> billsSorted = new ArrayList<Bill>();
		billsSorted.sort(new Comparator<Bill>() {

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
		for(Bill b : bills)
		{
			if(b.getCathegory().equals(name))
			{
				billsSorted.add(b);
			}
		}
		model.addAttribute("bills", billsSorted);
		return "cathegoryPage";
	}

}
