package org.egeloqua.model;

public class Minimal {
	public String id;
	public String name;
	public String description;
	public String folderId;
	public String sourceTemplateId;
	public String createdBy;
	public String updatedBy;

	public String createdAt;
	public String updatedAt;
	public String accessedAt;
	public String scheduledFor;

	 public String permissions; // InstancePermissions - union of "read","write","fullControl"
	 public String depth; //RequestDepth -union of "minimal", "partial", "complete"
}
