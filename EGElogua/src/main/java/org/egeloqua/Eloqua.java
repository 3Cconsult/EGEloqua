package org.egeloqua;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.egeloqua.model.data.Contact;
import org.egeloqua.model.data.Account;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class Eloqua {
	public String loginSite;
	public String loginUser;
	public String loginPass;
	public RestClient client;
	
	public Eloqua(String loginSite, String loginUser, String loginPass) {
		client = new RestClient("EGTEST\\API.Test","VInter1234","https://secure.p01.eloqua.com/api/rest/2.0");
	}
	
	public Contact[] getContactsByEmailQuery(String email) {
		Contact[] c = new Contact[1];
		
		try {
			// Use the basic Rest client
			String sResponse = client.execute("/data/contacts?depth=complete&search=" + URLEncoder.encode("emailaddress=" + email, "UTF-8"), "GET", null);
			JsonElement je = new JsonParser().parse(sResponse);
			
			// Dive into the elements
			JsonElement json = je.getAsJsonObject().get("elements");
			JsonArray array = json.getAsJsonArray();
			Iterator<JsonElement> iterator = array.iterator();
			List<Contact> contacts = new ArrayList<Contact>();
			
			int i = 0;
			while(iterator.hasNext()){
				i++;
				
			    JsonElement json2 = (JsonElement)iterator.next();
			    Gson gson2 = new Gson();
			    Contact contact = gson2.fromJson(json2, Contact.class);
			    contacts.add(contact);
			}

			
			if(contacts.size() > 1) {
				c = newArrSize(c, i);
			}
			
			i = 0;
			for(Contact c2 : contacts) {
				c[i] = c2;
				i++;
			}
			
			return c;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Contact getContact(Contact c) {
		try{
			Gson gson = new Gson();
			String sResponse = client.execute("/data/contact/" + c.id, "GET");
			
			return gson.fromJson(sResponse, Contact.class);
		}catch (Exception e) {
	          e.printStackTrace();
	          return null;
		}
	}
	
	public Contact updateContact(Contact c) {
		try{
			Gson gson = new Gson();
			String sRequest = gson.toJson(c);
			String sResponse = client.execute("/data/contact/" + c.id, "PUT", sRequest);
			
			return gson.fromJson(sResponse, Contact.class);
		}catch (Exception e) {
	          e.printStackTrace();
	          return null;
		}
	}
	
	public Contact[] newArrSize(Contact[] c, int newSize) {
		Contact[] temp = new Contact[newSize];
		int length = c.length;
		
		for (int j = 0; j < length; j++) {
			temp[j] = c[j];
		}
		
		c = temp;
		return c;
	}
	
	public List <Account> getAccounts(String name){
		try {
			// Use the basic Rest client
			String sResponse = client.execute("/data/accounts?depth=complete&search=" + URLEncoder.encode("name=" + name, "UTF-8"), "GET", null);
			JsonElement je = new JsonParser().parse(sResponse);
			
			// Dive into the elements
			JsonElement json = je.getAsJsonObject().get("elements");
			JsonArray array = json.getAsJsonArray();
			Iterator<JsonElement> iterator = array.iterator();
			List<Account> accounts = new ArrayList<Account>();
			
			while(iterator.hasNext()){
			    JsonElement json2 = (JsonElement)iterator.next();
			    Gson gson2 = new Gson();
			    Account account = gson2.fromJson(json2, Account.class);
			    accounts.add(account);
			}
			return accounts;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}
}
