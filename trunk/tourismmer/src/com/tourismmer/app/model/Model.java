package com.tourismmer.app.model;

import com.tourismmer.app.constants.Constants;

public abstract class Model {
	
	public String statusCode = Constants.EMPYT;
	
	public String statusText = Constants.EMPYT;
	
	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

}
