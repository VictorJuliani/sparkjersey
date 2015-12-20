package com;

import static spark.Spark.awaitInitialization;
import static spark.Spark.staticFileLocation;

import org.reflections.Reflections;

import com.resource.IResource;

public class Server
{
	private static final String PACKAGE = "com.resource";
	
	public static void main(String[] args)
	{		
		staticFileLocation("/public"); // TODO config
		
		for(Class<? extends com.resource.IResource> res : new Reflections(PACKAGE).getSubTypesOf(IResource.class))
		{
			try 
			{
				res.newInstance().init();
			} 
			catch (Exception e) 
			{
				System.out.println("Bad resource at: " + res.getSimpleName());
				e.printStackTrace();
			}
		}		
		
		awaitInitialization();
	}
}
