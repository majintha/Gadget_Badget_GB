package com;

import model.buyer;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON
import com.google.gson.*; 

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 
@Path("/buyers")

public class buyerService {
	buyer buyerObj = new buyer(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readBuyers() 
	 { 
		return buyerObj.readBuyers();  
	 } 
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertBuyer(
		 @FormParam("buyerName") String buyerName,
		 @FormParam("buyerGender") String buyerGender,
		 @FormParam("buyerContactNo") String buyerContactNo, 
		 @FormParam("buyerEmail") String buyerEmail,
		 @FormParam("buyerAddress") String buyerAddress)
		 
	{ 
		String output = buyerObj.insertBuyer(buyerName, buyerGender, buyerContactNo, buyerEmail, buyerAddress); 
		return output; 
	}	
	
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateBuyer(String buyerData) 
	{
        //Convert the input string to a JSON object
         JsonObject buyerObject = new JsonParser().parse(buyerData).getAsJsonObject();
         
        //Read the values from the JSON object
         String buyerID = buyerObject.get("buyerID").getAsString();
         String buyerName = buyerObject.get("buyerName").getAsString();
         String buyerGender = buyerObject.get("buyerGender").getAsString();
         String buyerContactNo = buyerObject.get("buyerContactNo").getAsString();
         String buyerEmail = buyerObject.get("buyerEmail").getAsString();
         String buyerAddress = buyerObject.get("buyerAddress").getAsString();
        
         String output = buyerObj.updateBuyer(buyerID, buyerName, buyerGender, buyerContactNo, buyerEmail, buyerAddress);
         return output;
    }
	
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteBuyer(String buyerData) 
	{ 
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(buyerData, "", Parser.xmlParser()); 
		 
		//Read the value from the element <itemID>
		 String buyerID = doc.select("buyerID").text(); 
		 String output = buyerObj.deleteBuyer(buyerID); 
		 return output; 
	}
	
}
