package org.egeloqua.model.assets;
import org.egeloqua.model.*;
import java.util.ArrayList;

public class CampaignElement extends Minimal {
	public Position position;
	public ArrayList <CampaignOutputTerminal> outputTerminals = new ArrayList <CampaignOutputTerminal>();
	public Integer contactCount;
}
