package com.workingtheory.akka;

import akka.actor.ActorSystem;
import akka.actor.TypedActor;
import akka.actor.TypedActorExtension;
import akka.actor.TypedProps;
import com.workingtheory.akka.actors.HelloWorldTypedActor;
import com.workingtheory.akka.actors.HelloWorldTypedActorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class TypedActorTest
{
	private static final Logger logger = LogManager.getLogger(TypedActorTest.class);

	@Test
	public void testTypedActor()
	{
		logger.info("Initializing actor system .. ");

		// Setting up Akka actor system
		ActorSystem system = ActorSystem.create("akka-typed-actor");

		logger.debug("Actor system '{}' initialized .. ", system.name());

		logger.debug("Creating TypedActor extension .. ");

		// Setting up TypedActor extension
		TypedActorExtension extension = TypedActor.get(system);

		// Creating a typed actor
		HelloWorldTypedActor actorProxy = extension.typedActorOf(new TypedProps<>(HelloWorldTypedActor.class, HelloWorldTypedActorImpl.class));

		logger.debug("Created actor 'HelloWorldTypedActor' .. ");

		logger.debug("Sending message .. ");

		actorProxy.sayHello("Hello World!");

		logger.debug("Message send successfully .. ");

		logger.debug("Shutting down Actor system '{}' .. ", system.name());

		system.terminate();

		logger.debug("Actor system '{}' shut down complete .. ", system.name());
	}
}
