package org.childcare;

import java.util.Set;
import com.mongodb.*;
import com.mongodb.client.*;


public class ChildCareMain {

	public ChildCareMain()
	{
		
	}
	
	public void Connect(String user, String password)
	{
		String childcareURL = "mongodb://" + user + ":" + password + "@ds117830.mlab.com:17830/childcare";
		try
		{
			MongoClientURI uri = new MongoClientURI(childcareURL);
			System.out.println("Url: " + childcareURL);
			MongoClient mongo = new MongoClient( uri );
			MongoDatabase db = mongo.getDatabase("childcare");
						
	        for (String colName : db.listCollectionNames()) {
	            System.out.println("\t + Collection: " + colName);
	        }
		}
		catch (Exception e)
		{
			
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChildCareMain ccmain = new ChildCareMain();
		ccmain.Connect(args[0], args[1]);

		try
		{
			ChildCareApp.runServer();
		}
		catch (Exception e)
		{
			
		}
		
	}

}
