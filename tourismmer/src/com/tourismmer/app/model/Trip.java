package com.tourismmer.app.model;


public class Trip {
	
    private Long idTrip;

    private String destination;

    private String a;

    private String k;

    private Long status;
    
    public Trip() {
    }
    
	public Long getIdTrip() {
		return idTrip;
	}

	public void setIdTrip(Long idTrip) {
		this.idTrip = idTrip;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getK() {
		return k;
	}

	public void setK(String k) {
		this.k = k;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

}
