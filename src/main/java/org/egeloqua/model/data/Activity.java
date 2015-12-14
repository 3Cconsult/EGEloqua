package org.egeloqua.model.data;
import java.util.Map;
import java.util.HashMap;

public class Activity {
	public String id;
	public String contact;
	public String asset;
	public Integer activityDate;
	// TODO: must be union
	public String activityType;
	public String assetType;
	public Map <String,String> details = new HashMap <String,String>();
}
