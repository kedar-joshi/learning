package com.workingtheory.remote;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RemoteActorTest
{
	private static final Logger logger = LogManager.getLogger(RemoteActorTest.class);

	private ActorSystem system;
	private static ActorRef    localActor;

	@Before
	public void initialize()
	{
		logger.info("Initializing actor system .. ");

		// Setting up Akka actor system
		system = ActorSystem.create("akka-remote-actor");

		logger.debug("Actor system '{}' initialized .. ", system.name());

		localActor = system.actorOf(Props.create(LocalActor.class), "Akka.Local.Actor");
	}

	@Test
	public void testRemoteActor()
	{
		logger.debug("Sending message .. ");

		localActor.tell("start", localActor);

		try
		{
			Thread.sleep(10000);
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
		}

		logger.debug("Message sent successfully .. ");

	}

	@After
	public void close()
	{
		logger.debug("Shutting down Actor system '{}' .. ", system.name());

		system.terminate();
	}

	public static class LocalActor
			extends UntypedActor
	{
		private ActorRef remoteActor = context().actorFor("akka.tcp://akka-remote-actor@127.0.0.1:5150/user/Akka.Remote.Actor");

		public void onReceive(Object message) throws Throwable
		{
			logger.info("Local Actor :: Received : {}", message);

			if ("start".equals(message))
			{
				remoteActor.tell("Local Actor :: Message from a galaxy far far away !!!", localActor);
			}
		}
	}

	public static class User
	{
		public String firstName;
		public String lastName;

		public User(String firstName, String lastName)
		{
			this.firstName = firstName;
			this.lastName = lastName;
		}

		public String toString()
		{
			final StringBuilder sb = new StringBuilder("User{");
			sb.append("firstName='").append(firstName).append('\'');
			sb.append(", lastName='").append(lastName).append('\'');
			sb.append('}');
			return sb.toString();
		}
	}

}
