package org.egeloqua.model.data;

import java.util.ArrayList;
import org.egeloqua.model.*;

public class Account extends Minimal{
	public String address1;
	public String address2;
	public String address3;
	public String city;
	public String province;
	public String postalCode;
	public String country;
	public String businessPhone;
	public ArrayList <FieldValue> fieldValues = new ArrayList <FieldValue>();
}
