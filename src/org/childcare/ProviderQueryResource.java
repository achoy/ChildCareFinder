package org.childcare;


import org.restlet.representation.*;
import org.restlet.ext.json.JsonRepresentation;

import com.mongodb.*;

public class ProviderQueryResource extends APIResource {
	
	BasicDBObject _query;
	
	private BasicDBObject getQueryData() { return _query; }
	private void setQuery(BasicDBObject query) { _query = query; }
		
	private Boolean lookup(String key, String dataField)
	{
		String field = query(key);
		if (!Helpers.isNullOrEmpty(field))
		{
			setQuery(makeDBObject(dataField, makeKeyArray("$in", makeArray(new String[]{ field }))));
			return true;
		}
		return false;
	}
	
	@Override
	protected Representation get()
	{
		ChildCareDS ds = this.getParent().getData();
		BasicDBObject query = new BasicDBObject();
		
		// take in some query if presented on command line
		
		/*String zipCode = query("zip");
		if (!Helpers.isNullOrEmpty(zipCode))
		{
			query = makeDBObject("zip", makeKeyArray("$in", makeArray(new String[]{ zipCode })));
		}*/
		if (lookup("zip", "zip"))
			query = getQueryData();
		else if (lookup("city", "city"))
			query = getQueryData();
		
		System.out.println("query = " + query.toJson());
		
		JsonRepresentation result = ds.queryJSON("providers", query, 100);
		return result;
	}
	
}
