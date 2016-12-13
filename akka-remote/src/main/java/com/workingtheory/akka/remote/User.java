package com.workingtheory.akka.remote;

import java.io.Serializable;

public class User implements Serializable
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