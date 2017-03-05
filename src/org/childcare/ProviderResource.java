package org.childcare;

import java.util.Map;

import org.restlet.data.*;
import org.restlet.representation.*;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import com.mongodb.BasicDBObject;

public class ProviderResource extends ServerResource {

	private Map<String, String> _queryParams;
	
	@Override
	protected void doInit() throws ResourceException
	{
		Form dataForm = getQuery();
		_queryParams = dataForm.getValuesMap();
	}
	
	protected String query(String key)
	{
		String result = _queryParams.get(key);
		return (result != null) ? result :
			_queryParams.get(key.toUpperCase());
	}
	
	@Override
	protected Representation get()
	{
		ChildCareDS ds = getParent().getData();
		BasicDBObject query = new BasicDBObject();
		String result = ds.query("providers", query);
		
		System.out.println("Results: " + result);
		return new StringRepresentation(result, MediaType.APPLICATION_ALL_JSON);
	}
	
	protected ChildCareApp getParent()
	{
		return (ChildCareApp) this.getApplication();
	}
}
