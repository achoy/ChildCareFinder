package org.childcare;

import org.restlet.representation.FileRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ServerResource;

public class FileResource extends ServerResource {

	public FileResource()
	{
		
	}
	
	@Override
	protected Representation get()
	{
		return getPage("/static/");
	}
	
	private Representation getPage(String pageName)
	{
		return getPageOrResource(pageName);
	}
	
	private Representation getPageOrResource(String path)
	{
		String filename = getAttribute(ChildCareApp.FILENAME_PARAM);
		if (Helpers.isNullOrEmpty(filename))
			return null;
		
		String localPath = Helpers.getLocalPath(path, filename);
		if (!Helpers.fileExist(localPath))
		{
			System.out.println("Cannot open file " + localPath);
			return null;
		}
		
		return new FileRepresentation(localPath, Helpers.resolveMediaType(filename));
	}
	
	protected ChildCareApp getParent()
	{
		return (ChildCareApp) this.getApplication();
	}
}
