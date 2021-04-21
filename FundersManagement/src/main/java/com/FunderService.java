package com;

import model.Funder;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Funders")

public class FunderService {
	Funder funderObj = new Funder();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readFunder()
	{
	return funderObj.readFunders();
	}
	
	@POST    
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertFunder( 
		 @FormParam("funderName") String funderName,
		 @FormParam("funderEmail") String funderEmail,
		 @FormParam("funderTel") String funderTel,
		 @FormParam("funderGender") String funderGender,
		 @FormParam("funderDonation") String funderDonation,
		 @FormParam("funderDesc") String funderDesc)
		 
	{ 
		String output = funderObj.insertFunder(funderName,funderEmail,funderTel,funderGender,funderDonation,funderDesc); 
		return output; 
	}	
	
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateFunder(String funderData) 
	{ 
		//Convert the input string to a JSON object 
		 JsonObject funderObject = new JsonParser().parse(funderData).getAsJsonObject(); 
		//Read the values from the JSON object
		 String funderID = funderObject.get("funderID").getAsString();  
		 String funderName = funderObject.get("funderName").getAsString();
		 String funderEmail = funderObject.get("funderEmail").getAsString();
		 String funderTel = funderObject.get("funderTel").getAsString();
		 String funderGender = funderObject.get("funderGender").getAsString();
		 String funderDonation = funderObject.get("funderDonation").getAsString();   
		 String funderDesc = funderObject.get("funderDesc").getAsString(); 
		 
		 String output = funderObj.updateFunder(funderID, funderName, funderEmail, funderTel ,funderGender , funderDonation, funderDesc); 
		 return output; 
	}
	
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteFunder(String funderData) 
	{ 
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(funderData, "", Parser.xmlParser()); 
		 
		//Read the value from the element <itemID>
		 String funderID = doc.select("funderID").text(); 
		 String output = funderObj.deleteFunder(funderID); 
		 return output; 
	}
}
