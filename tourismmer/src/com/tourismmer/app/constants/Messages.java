package com.tourismmer.app.constants;

public enum Messages {
	
	SUCCESS ("001","Success"),
	USER_PASS_INVALID ("002","User or Pass Invalid"),
	PARAMETERS_REQUIRED ("003","Parameters Required"),
	ERROR_QUERYING_DATABASE ("004","Error Querying the Database"),
	USER_NOT_REGISTERED ("005","User not Registered"),
	QUERY_NOT_FOUND ("006","Record not Found"),
	CONSTRAINT_VIOLATION_EXCEPTION ("007","Constraint Violation Exception"),
	ERROR_SEND_EMAIL ("008","Error Send Email"),
	SUCCESS_UNDO ("009","Success undo");
	
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
