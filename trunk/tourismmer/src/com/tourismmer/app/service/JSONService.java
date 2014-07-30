package com.tourismmer.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.tourismmer.app.Track;

@Path("/json/metallica")
public class JSONService {

	@GET
	@Path("/getTrackInJSON")
	@Produces(MediaType.APPLICATION_JSON)
	public Track getTrackInJSON() {

		Track track = new Track();
		track.setTitle("Enter Sandman");
		track.setSinger("Metallica");

		return track;

	}
	
	@POST
	@Path("/teste")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Track> getListTrackInJSON(String param) {
		
		System.out.println(param);
        
//	    Type listType = new TypeToken<List<Track>>() {}.getType();
//	    List<Track> target = new ArrayList<Track>();
//
//	    
//	    target = gson.fromJson(param, listType);
	    

		Track track = new Track();
		track.setTitle("Enter Sandman 1");
		track.setSinger("Metallica 1");
		
		Track track2 = new Track();
		track2.setTitle("Enter Sandman 2");
		track2.setSinger("Metallica 2");
		
		Track track3 = new Track();
		track3.setTitle("Enter Sandman 3");
		track3.setSinger("Metallica 3");
		
		List<Track> lista = new ArrayList<Track>();
		
		lista.add(track);
		lista.add(track2);
		lista.add(track3);

		return lista;

	}

	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTrackInJSON(Track track) {

		String result = "Track saved : " + track;
		return Response.status(201).entity(result).build();
		
	}
	
	// The Java method will process HTTP GET requests
	@GET
	// The Java method will produce content identified by the MIME Media
	// type "text/plain"
	@Produces("text/plain")
	public String getClichedMessage() {
	    // Return some cliched textual content
	    return "Hello World";
	}
	
}