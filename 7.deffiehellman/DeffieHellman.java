/*

Name : Rohit Siddheshwar
Roll no. : 6787

*/

import java.util.*;

class DiffieHellman
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the value of Xa & Xb");
		int Xa=sc.nextInt();
		int Xb=sc.nextInt();
		System.out.println("Enter a Prime no. p");
		int p=sc.nextInt();
		System.out.println("Enter Primitive Root a, such that a<p");
		int a=sc.nextInt();
		int Ya=(int)((Math.pow(a,Xa))%p);
		int Yb=(int)((Math.pow(a,Xb))%p);
		int Ka=(int)((Math.pow(Yb,Xa))%p);
		int Kb=(int)((Math.pow(Ya,Xb))%p);
		if(Ka==Kb)
		{
			System.out.println("Transmission successful");
		}
		else
		{
			System.out.println("Transmission failed");
		}
	}
}