package com.workingtheory.akka.actors;

import akka.actor.UntypedActor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HelloWorldActor
		extends UntypedActor
{
	private static final Logger logger = LogManager.getLogger(HelloWorldActor.class);

	public HelloWorldActor(String argument)
	{
	}

	@Override
	public void preStart()
	{
		logger.debug("Before '{}' start .. ", HelloWorldActor.class);
	}

	public void onReceive(Object message) throws Throwable
	{
		logger.info("Received message : '{}'", message);
	}
}