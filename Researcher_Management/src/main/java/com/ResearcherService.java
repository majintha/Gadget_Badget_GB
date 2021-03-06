package com;
import model.Researcher;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document; 

@Path("/Researchers")
public class ResearcherService {

	Researcher researcherObj = new Researcher();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readResearchers()
	 {
		return researcherObj.readResearchers(); 
	 }
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertResearcher(
			@FormParam("researcherName") String researcherName, 
			 @FormParam("researcherEmail") String researcherEmail, 
			 @FormParam("researcherNumber") String researcherNumber,
			 @FormParam("researcherAddress") String researcherAddress,
			 @FormParam("researcherProductType") String researcherProductType,
			 @FormParam("researcherReDate") String researcherReDate)
		 
	{ 
		String output = researcherObj.insertResearcher(researcherName, researcherEmail, researcherNumber, researcherAddress, researcherProductType, researcherReDate); 
		return output; 
	}	
	
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateResearcher(String researcherData) 
	{ 
		//Convert the input string to a JSON object 
		 JsonObject researcherObject = new JsonParser().parse(researcherData).getAsJsonObject(); 
		//Read the values from the JSON object
		 String researcherID = researcherObject.get("researcherID").getAsString(); 
		 String researcherName = researcherObject.get("researcherName").getAsString(); 
		 String researcherEmail = researcherObject.get("researcherEmail").getAsString(); 
		 String researcherNumber = researcherObject.get("researcherNumber").getAsString();   
		 String researcherAddress = researcherObject.get("researcherAddress").getAsString();
		 String researcherProductType = researcherObject.get("researcherProductType").getAsString();
		 String researcherReDate = researcherObject.get("researcherReDate").getAsString();
		 
		 String output = researcherObj.updateResearcher(researcherID, researcherName, researcherEmail, researcherNumber, researcherAddress, researcherProductType, researcherReDate); 
		 return output; 
	}
	
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteResearcher(String researcherData) 
	{ 
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(researcherData, "", Parser.xmlParser()); 
		 
		//Read the value from the element <itemID>
		 String researcherID = doc.select("researcherID").text(); 
		 String output = researcherObj.deleteResearcher(researcherID); 
		 return output; 
	}
}
