package org.childcare;

import java.util.Map;

import org.restlet.data.Form;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.json.*;

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
	
	protected JsonRepresentation replyErrorMessage(String code, String message)
	{
		JSONObject result = new JSONObject();
		result.put("code", code);
		result.put("message", message);
		System.out.println("Response: " + code + " " + message);
		return new JsonRepresentation(result);
	}
}
