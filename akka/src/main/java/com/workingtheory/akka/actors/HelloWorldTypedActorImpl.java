package com.workingtheory.akka.actors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HelloWorldTypedActorImpl
		implements HelloWorldTypedActor
{
	private static final Logger logger = LogManager.getLogger(HelloWorldTypedActorImpl.class);

	public void sayHello(String message)
	{
		logger.info("Received message : '{}'", message);
	}

	public void onReceive(Object message) throws Throwable
	{
		sayHello((String) message);
	}
}