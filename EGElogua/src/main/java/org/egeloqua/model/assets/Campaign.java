package org.egeloqua.model.assets;

import java.util.ArrayList;
import org.egeloqua.model.*;

public class Campaign extends Minimal{
	//TODO: convert to datetime
	public String startAt;
	public String endAt;
	public Double budgetedCost;
	public Double actualCost;
	public Boolean isMemberAllowedReEntry;
	public ArrayList <FieldValue> fieldValues = new ArrayList <FieldValue>();
	public ArrayList <CampaignElement> elements = new ArrayList <CampaignElement>();
	public Boolean isReadOnly;
	public String campaignType;
	public String product;
	public String region;
	public String crmId;
	public Boolean isSyncedWithCRM;
	public Boolean isIncludedInROI;	
}
