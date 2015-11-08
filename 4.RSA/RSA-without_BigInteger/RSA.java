//Rutuja Pakhare
//6774
//RSA


import java.util.*;
import java.math.*;

class RSA{

	public static void main(String[] args){
		long startTime = System.currentTimeMillis();
		Scanner sc = new Scanner(System.in);
		long[] record = {7,11,13,17,19,23,29,31,37,41};
		//for P
		int g = ((int)(Math.random()*10))%10;
		long p = record[g];
		System.out.println("P : " + p);
		record[g] = record[(g+1)%10];
		//for Q
		g = ((int)(Math.random()*10))%10;
		long q = record[g];
		System.out.println("Q : " + q);
		record[g] = record[(g+1)%10];

		long n = p*q;
		System.out.println("N : " + n);
		long phi = (p-1)*(q-1);
		System.out.println("PHI : " + phi);
		//for E
		g = ((int)(Math.random()*10))%10;
		long e = record[g];
		System.out.println("E : " + e);
		record[g] = record[(g+1)%10];

		long d = inverse(e,phi);
		System.out.println("D : " + d);

		long midstart = System.currentTimeMillis();
		System.out.print("Enter number to encrypt : ");
		int M = sc.nextInt();
		long midend = System.currentTimeMillis();
		
		BigInteger m = new BigInteger(String.valueOf(M));
		BigInteger E = new BigInteger(String.valueOf(e));
		BigInteger N = new BigInteger(String.valueOf(n));
		BigInteger D = new BigInteger(String.valueOf(d));
		BigInteger enc = m.modPow(E,N);
		System.out.println("Encryption : " + enc.toString());
		BigInteger dec = enc.modPow(D,N);
		System.out.println("Decryption : " + dec.toString());

		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime - midend + midstart;
		System.out.println("\n\nRun-Time : " + totalTime);
	}

	//Extended Eucledian Algorithm
	public static long[] gcd(long p, long q) {
		if (q == 0)
        	return new long[] { p, 1, 0 };
    	long[] vals = gcd(q, p % q);
    	long d = vals[0];
    	long a = vals[2];
    	long b = vals[1] - (p / q) * vals[2];
    	return new long[] { d, a, b };
	}

	//Modular Inverse
   	public static long inverse(long k, long n) {
		long[] vals = gcd(k, n);
		long d = vals[0];
		long a = vals[1];
		long b = vals[2];
		if (d > 1) { System.out.println("Inverse does not exist."); return 0; }
		if (a > 0) return a;
			return n + a;
	}
}

/*

P : 23
Q : 11
N : 253
PHI : 220
E : 13
D : 17
Enter number to encrypt : 56
Encryption : 199
Decryption : 56


Run-Time : 28


*/