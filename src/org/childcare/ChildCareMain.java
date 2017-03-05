package org.childcare;

import java.util.Set;
import com.mongodb.*;
import com.mongodb.client.*;


public class ChildCareMain {

	public ChildCareMain()
	{
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChildCareMain ccmain = new ChildCareMain();

		try
		{
			ChildCareApp.runServer(args);
		}
		catch (Exception e)
		{
			
		}
		
	}

}
