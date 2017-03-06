package org.childcare;

import java.util.Map;

import org.restlet.data.Form;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.json.*;
import org.bson.*;
import com.mongodb.*;

public class APIResource extends ServerResource {

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

    protected void addParam(String key, String value) {
    	_queryParams.put(key, value);
    }
    
	protected ChildCareApp getParent()
	{
		return (ChildCareApp) this.getApplication();
	}
	
	public BsonArray makeArray(String[] vals)
	{
		BsonArray arr = new BsonArray();
		for (String v : vals)
		{
			arr.add(new BsonString(v));
		}
		return arr;
	}
	
	public BsonArray makeRegExpArray(String[] vals)
	{
		BsonArray arr = new BsonArray();
		for (String v : vals)
		{
			arr.add(new BsonRegularExpression(v));
		}
		return arr;
	}
	
	public BsonDocument makeKeyArray(String key, BsonArray arr)
	{
		BsonDocument bd = new BsonDocument();
		bd.append(key, arr);
		return bd;
	}
		
	public BasicDBObject makeDBObject(String key, BsonValue val)
	{
		BasicDBObject dbObject = new BasicDBObject();
		dbObject.put(key, val);
		return dbObject;
	}
	
	protected JsonRepresentation replyErrorMessage(String code, String message)
	{
		JSONObject result = new JSONObject();
		result.put("code", code);
		result.put("message", message);
		System.out.println("Response: " + code + " " + message);
		return new JsonRepresentation(result);
	}
}
