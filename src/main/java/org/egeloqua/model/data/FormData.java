package org.egeloqua.model.data;
import java.util.ArrayList;
import org.egeloqua.model.*;

public class FormData {
	public String submittedByContactId;
	public ArrayList <FieldValue> fieldValues = new ArrayList <FieldValue>();	
	// TODO: convert to datetime
	public String submittedAt;
}
