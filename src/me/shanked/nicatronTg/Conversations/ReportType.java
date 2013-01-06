package me.shanked.nicatronTg.Conversations;

public enum ReportType {
	SERVER_MISCONFIGURATION("Server misconfiguration/misc. game breaking bug"),
	SERVER_ATTACK("Server attack/grief team/spam/etc."),
	OTHER("Other");
	
	private String name = ""; //Pretty name for use in sending to the user
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	ReportType(String name) {
		this.name = name;
	}
	
	
}
