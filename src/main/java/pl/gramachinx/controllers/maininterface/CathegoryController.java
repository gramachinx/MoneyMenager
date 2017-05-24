package pl.gramachinx.controllers.maininterface;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.xml.crypto.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import pl.gramachinx.domains.Bill;
import pl.gramachinx.domains.User;
import pl.gramachinx.repository.UserRepository;
import pl.gramachinx.services.DataInterface;

@Controller
@Secured("ROLE_CONFIGUSER")
public class CathegoryController {

	private DataInterface dataServ;
	
	
	@Autowired
	public CathegoryController(DataInterface dataServ) {
		super();
		this.dataServ = dataServ;
	}

	@GetMapping("/debets/cathegory")
	public String catPage()
	{
		return "cathegoryPage";
	}
	
	@GetMapping("/debets/cathegory/{name}")
	public String catPage2(@PathVariable String name, Model model, Principal principal)
	{
		List<Bill> billsSorted = dataServ.getSortedListByCathegory(dataServ.getUserData(principal), name);
		model.addAttribute("bills", billsSorted);
		return "cathegoryPage";
	}

}
