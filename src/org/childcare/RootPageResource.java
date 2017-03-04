package org.childcare;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class RootPageResource extends ServerResource {

	@Get
	public String represent()
	{
		return "welcome";
	}
}
