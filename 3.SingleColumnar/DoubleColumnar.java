//Rohit Siddheshwar
//6787
//Double Columnar

/*
* @author Rohit G Siddheshwar.
* Date : 13 - 9 - 2015.
*/

import java.util.*;

class DoubleColumnar{
	public static void main(String[] args){
		//Innitial Display
		System.out.println("_______________________________________________\n");
		System.out.println("                 Double Columnar                ");
		System.out.println("_______________________________________________\n");
		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.print("  Enter the key 1: ");
		String key = sc.nextLine();
		int kl = key.length();
		int[] keyI = new int[kl];
		boolean[] keyB = new boolean[kl];
		for(int i=0;i<kl;i++){
			keyI[i] = (int)((int)key.charAt(i) - (int)'a');
			keyB[i] = false;
		}
		int[] sortKey = toSortKey(keyI,kl,keyB);

		System.out.print("  Enter the Message : ");
		String message = sc.nextLine();
		int ml = message.length();
		int rowL = (int)Math.ceil((double)ml/kl);
		char[][] msg = toInputMessage(message,rowL,kl,ml,sortKey);
		System.out.println("_______________________________________________\n");
		System.out.println("  Result of Encryption Step 1 : \n");
		String pt = formNewPT(msg,rowL,kl);
		System.out.println("_______________________________________________\n");

		System.out.print("  Enter the key 2: ");
		String key2 = sc.nextLine();
		System.out.println("_______________________________________________\n");
		int kl2 = key2.length();
		int[] keyI2 = new int[kl2];
		boolean[] keyB2 = new boolean[kl2];
		for(int i=0;i<kl2;i++){
			keyI2[i] = (int)((int)key2.charAt(i) - (int)'a');
			keyB2[i] = false;
		}
		int[] sortKey2 = toSortKey(keyI2,kl2,keyB2);

		int ml2 = pt.length();
		int rowL2 = (int)Math.ceil((double)ml2/kl2);
		char[][] msg2 = toInputMessage(pt,rowL2,kl2,ml2,sortKey2);
		System.out.println("_______________________________________________\n");
		System.out.println("  Result of Encryption Step 2 : \n");
		String ct = formNewPT(msg2,rowL2,kl2);
		System.out.println("  Decrypting...");

		char[][] msgDec = decrypt(ct,rowL2,kl2,sortKey2);
		System.out.println("_______________________________________________\n");
		System.out.println("  Result of Decryption Step 1 : \n");
		String op1 = displayDecrypted(msgDec , rowL2, kl2);
		char[][] msgDec2 = decrypt(op1,rowL,kl,sortKey);
		System.out.println("_______________________________________________\n");
		System.out.println("  Result of Decryption Step 2 : \n");
		displayDecrypted(msgDec2 , rowL, kl);
		
	}

	public static int[] toSortKey(int[] keyI,int kl,boolean[] keyB){
		int min = 26;
		int[] sortKey = new int[kl];
		int index = -1;
		for(int i=0;i<kl;i++){
			min = 26;
			index = -1;
			for(int j=0;j<kl;j++){
				if(keyI[j] < min && !keyB[j]){
					min = keyI[j];
					index = j;
				}
			}
			sortKey[i] = index;
			keyB[index] = true;
		}
		return sortKey;
	}

	public static char[][] toInputMessage(String message,int rowL,int kl,int ml,int[] sortKey){
		char[][] msg = new char[rowL][kl];

		for(int i=0;i<kl;i++){
			for(int j=0;j<rowL;j++){
				if((j*kl + sortKey[i]) < ml)
					msg[j][i] = message.charAt(j*kl + sortKey[i]);
				else
					msg[j][i] = '-';
			}
		}
		return msg;
	}

	public static String formNewPT(char[][] msg,int rowL,int kl){
		String ct = "";
		for(int i=0;i<rowL;i++){
			System.out.print("\t");
			for(int j=0;j<kl;j++){
				System.out.print(msg[i][j] + " ");
				ct = ct + msg[i][j];
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("  " + ct);
		System.out.println("_______________________________________________\n");
		return ct;
	}

	public static char[][] decrypt(String s,int rowL,int kl,int[] sortKey){
		char[][] msgDec = new char[rowL][kl];
		for(int i=0;i<kl;i++){
			for(int j=0;j<rowL;j++){
				msgDec[j][sortKey[i]] = s.charAt(j*kl + i);
			}
		}
		return msgDec;
	}

	public static String displayDecrypted(char[][] msgDec,int rowL,int kl){
		String output = "";
		for(int i=0;i<rowL;i++){
			System.out.print("\t");
			for(int j=0;j<kl;j++){
				System.out.print(msgDec[i][j] + " ");
				output = output + msgDec[i][j];
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("  " + output);
		System.out.println("_______________________________________________\n");
		return output;
	}
}

/*

OUTPUT : 

_______________________________________________

                 Double Columnar                
_______________________________________________


  Enter the key 1: zinda
  Enter the Message : whatificonsidernottotellyouanything
_______________________________________________

  Result of Encryption Step 1 : 

	i t h a w 
	n o i c f 
	r e i d s 
	o t o t n 
	y l e l t 
	y n u a o 
	g n h i t 

  ithawnoicfreidsototnyleltynuaognhit
_______________________________________________

_______________________________________________

  Enter the key 2: fish
_______________________________________________

_______________________________________________

  Result of Encryption Step 2 : 

	i a t h 
	w i n o 
	c e f r 
	i o d s 
	t n o t 
	y l l e 
	t u y n 
	a n o g 
	h - i t 

  iathwinocefriodstnotylletuynanogh-it
_______________________________________________

  Decrypting...
_______________________________________________

  Result of Decryption Step 1 : 

	i t h a 
	w n o i 
	c f r e 
	i d s o 
	t o t n 
	y l e l 
	t y n u 
	a o g n 
	h i t - 

  ithawnoicfreidsototnyleltynuaognhit-
_______________________________________________

_______________________________________________

  Result of Decryption Step 2 : 

	w h a t i 
	f i c o n 
	s i d e r 
	n o t t o 
	t e l l y 
	o u a n y 
	t h i n g 

  whatificonsidernottotellyouanything
_______________________________________________


*/