package org.egeloqua;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.egeloqua.model.data.Account;
import org.egeloqua.Eloqua;
import java.util.List;

@RestController
@RequestMapping("/accountsAPI")
public class AccountsControllerREST {
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Account> findAccounts(
		@RequestParam(required = false) String search,
		@RequestParam(required = false) Integer count,
		@RequestParam(required = false) Integer page
	) {

	
		System.out.println("query1");
		Eloqua eloqua = new Eloqua("EGTEST", "API.Test", "VInter1234"); // Login on client instance
		List<Account> oListOfAccounts = eloqua.getAccounts(search,count,page); // Query Accounts
		System.out.println("query2");
		return oListOfAccounts;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Account addAccount(@RequestBody Account account){
		System.out.println("beforeInsert ");
		
		Eloqua eloqua = new Eloqua("EGTEST", "API.Test", "VInter1234"); // Login on client instance
		Account oAccount = eloqua.insertAccount(account);
		System.out.println("afterInsert ");

		return oAccount;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Account updateAccount(@RequestBody Account updatedAccount, @PathVariable Integer id) {
		System.out.println("beforeUpdate "+id);

		Eloqua eloqua = new Eloqua("EGTEST", "API.Test", "VInter1234"); // Login on client instance
		Account oAccount = eloqua.updateAccount(updatedAccount);
		System.out.println("afterUpdate "+id);
		return oAccount;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteAccount(@PathVariable Integer id) {
		System.out.println("beforeDelete "+id);
		Eloqua eloqua = new Eloqua("EGTEST", "API.Test", "VInter1234"); // Login on client instance
		eloqua.delAccount(id.toString());
		System.out.println("afterDelete "+id);
	}
}

