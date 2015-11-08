//Hill Cipher
//Rohit G Siddheshwar

import java.util.*;

class Hill{
	static int[][] ik;
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Message : ");
		String s = sc.nextLine();
		s = s.replaceAll(" ","");
		if(s.length()%2 != 0){
			s = s + "x";
		}
		char[] c = s.toCharArray();
		int[][] k = new int[2][2];
		ik = new int[2][2];

		k[0][0] = 3;
		k[0][1] = 3;
		k[1][0] = 2;
		k[1][1] = 5;
		//encryption
		String dc = encrypt(c,k);
		System.out.println("Encrypted Message : " + dc);

		//decrypt
		char[] dcc = dc.toCharArray();
		modularInverse(k);
		System.out.println("Decrypted Message : " + decrypt(dcc));
	}

	public static void print(int[][] k){
		System.out.println();
		for(int i=0;i<2;i++){
			for(int j=0;j<2;j++){
				System.out.print(k[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static String multiply(char a,char b,int[][] k){
		int ai = (int)((int)a - (int)'a');
		int bi = (int)((int)b - (int)'a');
		String x = String.valueOf((char)(((k[0][0]*ai + k[0][1]*bi)%26) + (int)'A')) 
		+ String.valueOf((char)(((k[1][0]*ai + k[1][1]*bi)%26) + (int)'A'));
		System.out.println(x);
		return x;
	}

	public static String encrypt(char[] c,int[][] k){
		print(k);
		String st = new String();
		for(int i=0;i<c.length;i+=2){
			st = st + multiply(c[i],c[i+1],k);
		}
		return st;
	}

	public static void modularInverse(int[][] k){
		ik[0][0] = k[1][1];
		ik[1][1] = k[0][0];
		ik[0][1] = 26 - k[0][1];
		ik[1][0] = 26 - k[1][0];
		int[] vals = gcd((k[0][0]*k[1][1] - k[0][1]*k[1][0]) , 26);
		ik[0][0] = (ik[0][0]*vals[1])%26;
		ik[0][1] = (ik[0][1]*vals[1])%26;
		ik[1][0] = (ik[1][0]*vals[1])%26;
		ik[1][1] = (ik[1][1]*vals[1])%26;
		print(ik);
	}

	public static String multiplyInverse(char a,char b){
		int ai = (int)((int)a - (int)'A');
		int bi = (int)((int)b - (int)'A');
		return String.valueOf((char)(((ik[0][0]*ai + ik[0][1]*bi)%26) + (int)'a')) 
		+ String.valueOf((char)(((ik[1][0]*ai + ik[1][1]*bi)%26) + (int)'a'));
	}

	public static String decrypt(char[] dc){
		String st = new String();
		for(int i=0;i<dc.length;i+=2){
			st = st + multiplyInverse(dc[i],dc[i+1]);
		}
		return st;
	}

	public static int[] gcd(int p, int q) {
    	if (q == 0)
        	return new int[] { p, 1, 0 };

    	int[] vals = gcd(q, p % q);
    	int d = vals[0];
    	int a = vals[2];
    	int b = vals[1] - (p / q) * vals[2];
    	return new int[] { d, a, b };
   }
}

/*

Enter Message : this is a message

3 3 
2 5 
AV
AC
AC
KI
OU
CK
EG
Encrypted Message : AVACACKIOUCKEG

15 17 
20 9 
Decrypted Message : thisisamessage


Enter Message : welcome to india

3 3 
2 5 
AM
NG
AK
RZ
OQ
WP
YQ
Encrypted Message : AMNGAKRZOQWPYQ

15 17 
20 9 
Decrypted Message : welcometoindia


*/