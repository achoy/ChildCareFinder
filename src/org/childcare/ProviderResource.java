package org.childcare;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.*;

import java.io.IOException;

import org.restlet.data.*;
import org.restlet.representation.*;

import com.mongodb.*;
import com.mongodb.util.*;
import org.json.*;

public class ProviderResource extends APIResource {

	@Override
	protected Representation get()
	{
		
		ChildCareDS ds = this.getParent().getData();
		BasicDBObject providerQuery = new BasicDBObject();
		String id = getAttribute("id");
		System.out.println("Looking for license = " + id);
		providerQuery.put("licenseNumber", id);
		String result = ds.query("providers", providerQuery);

		return new StringRepresentation(result, MediaType.APPLICATION_ALL_JSON);
	}
	
	@Put
	public Representation editProvider(JsonRepresentation newData)
	{
		ChildCareDS ds = this.getParent().getData();
		JSONObject result = new JSONObject();
		try {
			String jsonString = newData.getText();
			DBObject bson = (DBObject) JSON.parse(jsonString);
			
			ds.put("providers", bson);
			
			result.put("code", "200");
			result.put("message", "ok");
			
		} catch (IOException e) {			
			// lets get some error debug output
			result.put("code", "500");
			result.put("message", "Failed to process document: " + e.getMessage());
		}
		catch (MongoException me) {
			// cannot insert document
			result.put("code", "500");
			result.put("message", "Cannot insert document: " + me.getMessage());
		}

		return new JsonRepresentation(result);
	}
	
}
