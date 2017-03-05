/**
 * 
 */
package org.childcare;

import org.restlet.*;
import org.restlet.data.*;
import org.restlet.resource.ServerResource;
import org.restlet.routing.VirtualHost;
import java.util.logging.*;

import org.restlet.routing.Router;
import org.restlet.routing.TemplateRoute;
import org.restlet.routing.Variable;

/**
 * 
 *
 */
public class ChildCareApp extends Application {

	ChildCareDS _ccDS;
	Component _component;
	Router _router;

    public static String FILENAME_PARAM = "filename";

	public ChildCareApp()
	{
		_component = new Component();
		_router = new Router(getContext());
		
		addProtocol(getComponent(), Protocol.HTTP, 5000);
		
		init();
	}

	public Component getComponent() { return _component; }
	private Router getRouter() { return _router; }
	public ChildCareDS getData() { return _ccDS; }
	
	@Override
	public synchronized Restlet createInboundRoot()
	{		
		return getRouter();
	}
	
	public Context getContext()
	{
		return getComponent().getContext();
	}
	
	private void addLocation(VirtualHost vhost, String uriPattern, Restlet app)
	{
		if (uriPattern.equals("/"))
		{
			vhost.attach(app);
			setDefaultHost(vhost);
		}
		else
		{
			vhost.attach(uriPattern, app);
		}
	}
	
	private void setDefaultHost(VirtualHost defaultHost)
	{
		getComponent().setDefaultHost(defaultHost);
	}
	
	private void setDefaultLocation(Restlet app)
	{
		VirtualHost vhost = new VirtualHost(getContext());
		addLocation(vhost, "/", app);
	}
	
	private void addProtocol(Component c, Protocol protocol, int port)
	{
		System.out.println("Listening on localhost:" + port + " protocol:" + protocol.getName());
		c.getServers().add(protocol, port);
	}
	
	private void bind(String url, Class<? extends ServerResource> res)
	{
		getRouter().attach(url, res);
	}
	
	private void bindUrlAppendFilename(String url, Class<? extends ServerResource> res)
	{
		TemplateRoute route = getRouter().attach(String.format("%s/{%s}", url, FILENAME_PARAM), res);
		route.getTemplate().getVariables().put(FILENAME_PARAM, new Variable(Variable.TYPE_ALL));
	}
	
	private void init()
	{
		Logger logger = Logger.getLogger("org.restlet");
		for (Handler handler : logger.getParent().getHandlers())
		{
			if (handler.getClass().equals(java.util.logging.ConsoleHandler.class))
				handler.setLevel(Level.INFO);
		}
		
		bind("/api/providers", ProviderQueryResource.class);
		bind("/api/provider/{id}", ProviderResource.class);
		bindUrlAppendFilename("", FileResource.class);
	}
	
	public void start(String username, String password)
	{
		try
		{
			System.out.println("Starting engine:");
			
			_ccDS = new ChildCareDS();
			_ccDS.connect(username, password);
			
			setDefaultLocation(this);
			
			getComponent().start();
		}
		catch (Exception e)
		{
			
		}
	}
	
	public static void runServer(String[] args) throws Exception
	{
		ChildCareApp app = new ChildCareApp();
		if (args == null || args.length < 3)
		{
			System.out.println("Not enough parameters: username password web_page");
			return;
		}
		String username = args[0];
		String password = args[1];
		String webPath = args[2];
		Helpers.setWebPath(webPath);
		app.start(username, password);
	}
}
