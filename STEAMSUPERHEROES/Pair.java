package com.emailProject;

public class Pair 
{
	String name1;
	String name2;
	String emailMentor;
	String emailMentee;
	
	public Pair(String name1, String name2, String emailMentor, String emailMentee)
	{
		this.emailMentor = emailMentor;
		this.emailMentee = emailMentee;
		this.name1 = name1;
		this.name2 = name2;
	}
	
	public void printPair()
	{
		System.out.println("name1: " + name1 + ", name2: " + name2 + "\n");

	}
}
