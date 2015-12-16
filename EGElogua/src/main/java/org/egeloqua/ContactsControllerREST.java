package org.egeloqua;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.egeloqua.model.data.Contact;
import org.egeloqua.Eloqua;
import java.util.List;

@RestController
@RequestMapping("/contactsAPI")

public class ContactsControllerREST {
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Contact> findContacts() {
		System.out.println("query1");
		Eloqua eloqua = new Eloqua("EGTEST", "API.Test", "VInter1234"); // Login on client instance
		List<Contact> oListOfContacts = eloqua.getContacts("test*"); // Query Contacts
		System.out.println("query2");
		return oListOfContacts;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Contact addContact(@RequestBody Contact Contact){
		System.out.println("beforeInsertContact");
		
		Eloqua eloqua = new Eloqua("EGTEST", "API.Test", "VInter1234"); // Login on client instance
		Contact oContact = eloqua.insertContact(Contact);
		System.out.println("afterInsertContact");

		return oContact;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Contact updateContact(@RequestBody Contact updatedContact, @PathVariable Integer id) {
		System.out.println("beforeUpdateContact "+id);

		Eloqua eloqua = new Eloqua("EGTEST", "API.Test", "VInter1234"); // Login on client instance
		Contact oContact = eloqua.updateContact(updatedContact);
		System.out.println("afterUpdateContact "+id);
		return oContact;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteContact(@PathVariable Integer id) {
		System.out.println("beforeDeleteContact "+id);
		Eloqua eloqua = new Eloqua("EGTEST", "API.Test", "VInter1234"); // Login on client instance
		eloqua.delContact(id.toString());
		System.out.println("afterDeleteContact "+id);
	}
}

