package com.example;

public class Pair 
{
	String name1;
	String name2;
	
	public Pair(String name1, String name2)
	{
		this.name1 = name1;
		this.name2 = name2;
	}
	
	public void printPair()
	{
		System.out.println("name1: " + name1 + ", name2: " + name2 + "\n");
	}
}
