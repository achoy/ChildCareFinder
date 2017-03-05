package org.childcare;

import org.bson.Document;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import com.mongodb.client.MongoCursor;

public class ChildCareDS {

	MongoDatabase _db;
	
	public ChildCareDS()
	{
		
	}
	
	public void connect(String user, String password)
	{
		String childcareURL = "mongodb://" + user + ":" + password + "@ds117830.mlab.com:17830/childcare";
		try
		{
			MongoClientURI uri = new MongoClientURI(childcareURL);
			System.out.println("Url: " + childcareURL);
			MongoClient mongo = new MongoClient( uri );
			_db = mongo.getDatabase("childcare");
						
	        for (String colName : _db.listCollectionNames()) {
	            System.out.println("\t + Collection: " + colName);
	        }
		}
		catch (Exception e)
		{
			
		}
	}
	
	
	public String query(String collectionName, BasicDBObject query)
	{
		MongoCollection<Document> collection = _db.getCollection(collectionName);		
		if (collection == null)
			return "";
		
		FindIterable<Document> find = collection.find(query).limit(1000);
		if (find == null)
			return "";
		
		MongoCursor<Document> iterator = find.iterator();
		BasicDBList list = new BasicDBList();
		while (iterator.hasNext())
		{
			list.add(iterator.next());
		}
		
		return JSON.serialize(list);
	}
	
	public DBObject queryBSON(String collectionName, BasicDBObject query)
	{
		MongoCollection<DBObject> collection = _db.getCollection(collectionName, DBObject.class);
		
		FindIterable<DBObject> find = collection.find(query);
		if (find != null)
		{
			MongoCursor<DBObject> iterator = find.iterator();
			if (iterator.hasNext())
			{
				return iterator.next();
			}
		}
		return null;
	}
	
	public void put(String collectionName, DBObject newDocument) throws MongoException
	{
		MongoCollection<DBObject> collection = _db.getCollection(collectionName, DBObject.class);
		
		if (collection != null)
		{
			collection.insertOne(newDocument);
		}		
	}
}
