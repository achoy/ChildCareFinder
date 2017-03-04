/**
 * 
 */
package org.childcare;

import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;

/**
 * 
 *
 */
public class ChildCareApp extends Application {

	@Override
	public synchronized Restlet createInboundRoot()
	{
		Router router = new Router(getContext());
		
		// routes
		router.attach("/hello", RootPageResource.class);
		
		return router;
	}
	
	public ChildCareApp()
	{
		
	}
	
	public static void runServer() throws Exception
	{
		Component component = new Component();
		
		// add new HTTP server listening on 5000
		component.getServers().add(Protocol.HTTP, 5000);
		
		component.getDefaultHost().attach("/v1", new ChildCareApp());
		
		// start the component
		component.start();
	}
}
