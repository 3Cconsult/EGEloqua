package org.egeloqua;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.egeloqua.model.data.Account;
import org.egeloqua.Eloqua;
import java.util.List;

@RestController
@RequestMapping("/accountsAPI")

public class AccountsControllerREST {
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Account> findAccounts() {
		Eloqua eloqua = new Eloqua("EGTEST", "API.Test", "VInter1234"); // Login on client instance
		List<Account> oListOfAccounts = eloqua.getAccounts("te*"); // Query Accounts
		return oListOfAccounts;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Account addAccount(@RequestBody Account account){
		Eloqua eloqua = new Eloqua("EGTEST", "API.Test", "VInter1234"); // Login on client instance
		Account oAccount = eloqua.updateAccount(account);
		return oAccount;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Account updateAccount(@RequestBody Account updatedAccount, @PathVariable Integer id) {
		Eloqua eloqua = new Eloqua("EGTEST", "API.Test", "VInter1234"); // Login on client instance
		Account oAccount = eloqua.updateAccount(updatedAccount);
		return oAccount;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteAccount(@PathVariable Integer id) {
		// TODO: implement delete;
	}
  
}

