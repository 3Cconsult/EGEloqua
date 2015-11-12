package org.egeloqua;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.egeloqua.model.data.Account;
import org.egeloqua.Eloqua;
import java.util.List;

@Controller
public class AccountsController {

	@RequestMapping("/accounts")
    public String accounts() {
//        model.addAttribute("name", name);
        return "accounts";
    }

	@ModelAttribute("allAccounts")
	public List<Account> populateAccounts() {
	    return this.findAll();
	}

	
	public List <Account> findAll(){
		Eloqua eloqua = new Eloqua("EGTEST", "API.Test", "VInter1234"); // Login on client instance
		List<Account> accounts = eloqua.getAccounts("te*"); // Query Accounts
		return accounts;
	}
}

