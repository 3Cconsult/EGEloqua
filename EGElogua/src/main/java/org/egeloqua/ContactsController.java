package org.egeloqua;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.egeloqua.model.data.Contact;
import org.egeloqua.Eloqua;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ContactsController {

	@RequestMapping("/contacts")
    public String contacts() {
        return "contacts";
    }

	@ModelAttribute("allContacts")
	public List<Contact> populateContacts() {
	    return this.findAll();
	}

	public List <Contact> findAll(){
		Eloqua eloqua = new Eloqua("EGTEST", "API.Test", "VInter1234"); // Login on client instance
		List<Contact> contacts = new ArrayList<Contact>();
		Contact[] aCon = eloqua.getContactsByEmailQuery("*"); // Query Contacts
		for (Contact tmp : aCon){
			contacts.add(tmp);
		}
		return contacts;
	}
}

