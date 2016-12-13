package com.workingtheory.remote;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.workingtheory.akka.remote.RemoteActorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RemoteActorTest
{
	private static final Logger logger = LogManager.getLogger(RemoteActorTest.class);

	private ActorSystem system;
	private ActorRef    remoteActor;

	@Before
	public void initialize()
	{
		logger.info("Initializing actor system .. ");

		// Setting up Akka actor system
		system = ActorSystem.create("akka-remote-actor");

		logger.debug("Actor system '{}' initialized .. ", system.name());

		remoteActor = system.actorOf(Props.create(RemoteActorImpl.class), "Akka.Remote.Actor");
	}

	@Test
	public void testRemoteActor()
	{
		try
		{
			Thread.sleep(60000);
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
		}
	}

	@After
	public void close()
	{
		logger.debug("Shutting down Actor system '{}' .. ", system.name());

		system.terminate();
	}



}
