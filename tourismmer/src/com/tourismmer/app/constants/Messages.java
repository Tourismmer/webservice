package com.tourismmer.app.constants;

public enum Messages {
	
	REGISTER_SUCCESS ("001","Register Success"),
	USER_LOGGED ("002","User Logged"),
	USER_PASS_INVALID ("003","User or Pass Invalid"),
	PARAMETERS_REQUIRED ("004","Parameters Required"),
	ERROR_QUERYING_DATABASE ("005","Error Querying the Database"),
	USER_NOT_REGISTERED ("006","User not Registered"),
	UPDATE_SUCCESS ("007","Update Success"),
	QUERY_SUCCESS ("008","Query Success"),
	QUERY_NOT_FOUND ("009","Record not Found"),
	CONSTRAINT_VIOLATION_EXCEPTION ("010","Constraint Violation Exception"),
	LINK_RECOVER_PASS_SENDED ("011","Link Recover Pass Sended"), 
	ERROR_SEND_EMAIL ("012","Error Send Email"),
	PASS_CHANGED ("013","Changed password");
	
	private final String statusCode;
	
	private final String statusText;
	
	private Messages(String statusCodeParam, String statusTextParam) {
		this.statusCode = statusCodeParam;
		this.statusText = statusTextParam;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public String getStatusText() {
		return statusText;
	}

}
