package org.childcare;


import org.restlet.data.*;
import org.restlet.representation.*;

import com.mongodb.BasicDBObject;

public class ProviderQueryResource extends APIResource {
	
	@Override
	protected Representation get()
	{
		ChildCareDS ds = this.getParent().getData();
		BasicDBObject query = new BasicDBObject();
		String result = ds.query("providers", query);
		
		System.out.println("Results: " + result);
		return new StringRepresentation(result, MediaType.APPLICATION_ALL_JSON);
	}
	

}
