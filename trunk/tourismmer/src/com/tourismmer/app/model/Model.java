package com.tourismmer.app.model;

import com.tourismmer.app.constants.ViewConstants;

public abstract class Model {
	
	public String statusCode = ViewConstants.EMPYT;
	
	public String statusText = ViewConstants.EMPYT;
	
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
