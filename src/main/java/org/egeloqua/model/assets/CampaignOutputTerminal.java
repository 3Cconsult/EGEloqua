package org.egeloqua.model.assets;
import org.egeloqua.model.*;

public class CampaignOutputTerminal extends Minimal {
	public String terminalType; // TerminalType, union of "out", "yes", "no", "system"
	public String connectedType; // ElementType, union of "CampaignLandingPage", "CampaignEmail", "CampaignSegment", "CampaignWaitAction", "CampaignSubmitFormRule", "CampaignContactFilterMembershipRule", "CampaignEmailClickthroughRule", "CampaignRemoveFromCampaignAction", "CampaignAddToCampaignAction", "CampaignAddToProgramAction", "CampaignContactFieldComparisonRule", "CampaignInput", "CampaignEmailSentRule", "CampaignEmailOpenedRule", "CampaignWebSiteVisitRule", "CampaignContactListMembershipRule", "CampaignAddToContactListAction", "CampaignRemoveFromContactListAction", "CampaignForm", "CampaignMoveToCampaignAction", "CampaignMoveToProgramAction", "CampaignSendToCloudConnectorAction", "CampaignCustomObjectFieldComparisonRule"
	public String connectedId;
}
