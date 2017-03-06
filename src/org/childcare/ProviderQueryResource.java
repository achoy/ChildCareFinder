package org.childcare;


import org.restlet.representation.*;
import org.restlet.ext.json.JsonRepresentation;

import com.mongodb.BasicDBObject;

public class ProviderQueryResource extends APIResource {
	
	@Override
	protected Representation get()
	{
		ChildCareDS ds = this.getParent().getData();
		BasicDBObject query = new BasicDBObject();
		
		// take in some query if presented on command line
		
		JsonRepresentation result = ds.queryJSON("providers", query, 100);
		System.err.println("providers count = " + ds.getQueryCount());		
		return result;
	}
	
}
