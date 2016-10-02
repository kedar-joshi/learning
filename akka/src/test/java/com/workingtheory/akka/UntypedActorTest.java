package com.workingtheory.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.workingtheory.akka.actors.HelloWorldActor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class UntypedActorTest
{
	private static final Logger logger = LogManager.getLogger(UntypedActorTest.class);

	@Test
	public void testUntypedActor()
	{
		logger.info("Initializing actor system .. ");

		// Setting up Akka actor system
		ActorSystem system = ActorSystem.create("akka-untyped-actor");

		logger.debug("Actor system '{}' initialized .. ", system.name());

		// Creating an untyped actor
		ActorRef actor = system.actorOf(Props.create(HelloWorldActor.class, "hello-world-actor"));

		logger.debug("Created actor 'hello-world-actor' .. ");

		logger.debug("Sending message .. ");

		actor.tell("Hello World!", actor);

		logger.debug("Message send successfully .. ");

		logger.debug("Shutting down Actor system '{}' .. ", system.name());

		system.terminate();

		logger.debug("Actor system '{}' shut down complete .. ", system.name());
	}
}
