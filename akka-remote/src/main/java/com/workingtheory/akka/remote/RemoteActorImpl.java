package com.workingtheory.akka.remote;

import akka.actor.UntypedActor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RemoteActorImpl
		extends UntypedActor

{
	private static final Logger logger = LogManager.getLogger(RemoteActorImpl.class);

	public RemoteActorImpl()
	{
	}

	public RemoteActorImpl(String actor)
	{
		logger.info("Actor : {}", actor);
	}

	@Override
	public void preStart()
	{
		logger.debug("Before '{}' start .. ", RemoteActorImpl.class);
	}

	public void onReceive(Object message) throws Throwable
	{
		logger.info("Received message : '{}'", message);

		context().sender().tell(new User("Kedar", "Joshi"), null);
	}
}
