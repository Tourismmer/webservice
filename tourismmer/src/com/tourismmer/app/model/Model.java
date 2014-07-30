package com.tourismmer.app.model;

import com.tourismmer.app.util.ViewConstants;

public abstract class Model {
	
	public String statusCode = ViewConstants.VAZIO;
	
	public String statusText = ViewConstants.VAZIO;
	
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
