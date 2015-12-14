package org.egeloqua.model.data;
import java.util.ArrayList;
import org.egeloqua.model.*;
public class Contact extends org.egeloqua.model.Minimal {
	public String firstName;
	public String lastName;
	public String emailAddress;
	public String accountName;
	public String accountId;
	public String title;
	public String address1;
	public String address2;
	public String address3;
	public String city;
	public String province;
	public String postalCode;
	public String country;
	public String businessPhone;
	public String mobilePhone;
	public String fax;
	public String salesPerson;
	public ArrayList <FieldValue> fieldValues = new ArrayList <FieldValue>();		
	public boolean isSubscribed;
	public boolean isBounceback;
	//TODO: convert to datetime
	public String subscriptionDate;
	public String unsubscriptionDate;
	public String bouncebackDate;

}
