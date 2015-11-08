/*
*
* Name : Rohit Siddheshwar
* Title : Custom Ciphers
* 
*/

/*
* This encryption technique is based on the right shift property.
* There are two stages of encryption : 
* 	1. Every Individual letter is right-shifted and the lsb of
*	   each letter in block of 4 forms one letter which is a key.
*	2. Additive Cipher is implemented to create confusion. The
*	   Key is altered so as to make it a semi-public key encryption.
*/

import java.util.*;

class RightShiftKeyCipher{

	static String key = "";
	static String antikey = "";
	static String ct = "";
	static String ct2;
	static String newpt = "";

	static String[] reference = {"00000","00001","00010","00011","00100","00101","00110","00111","01000","01001","01010","01011","01100","01101","01110","01111","10000","10001","10010","10011","10100","10101","10110","10111","11000","11001","11010"};
	
	public static void main(String args[]){

		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.println();
		System.out.println(" Right Shift Key Cipher ");
		System.out.println("========================");
		System.out.println();
		System.out.print("Enter the Plain-Text : ");
		String plainText = sc.nextLine();
		plainText = plainText.replaceAll(" ","");
		System.out.println();
		encryptMessage(plainText);
		displayCT();

		System.out.println();
		System.out.print(" Do you want to Decrypt the above message ?(Y/n) : ");
		String decision = sc.nextLine();
		System.out.println();
		if(decision.equals("n") || decision.equals("N")){
		}
		else{
			decryptMessage();
			displayDeciphered();
		}
		System.out.println("--------------------END------------------------");

	}

	public static void encryptMessage(String pt){

		if(pt.length() % 4 != 0){
			int compensate = 4 - (pt.length() % 4);
			for(int j=0;j<compensate;j++){
				pt = pt + " ";
			}
		}
		char[] ptArray = pt.toCharArray();
		int[] ptInt = new int[pt.length()];

		//Traversing the blocks
		for(int i=0;i<pt.length();i += 4){

			char[] blockKey = new char[5];
			blockKey[0] = '0';
			//Traversing individual letters of each block
			for(int j=0;j<4;j++){
				if(ptArray[i + j] == ' '){
					ptInt[i + j] = 26;
				}
				else{
					ptInt[i + j] = (int)((int)ptArray[i + j] - (int)'a');
				}

				char[] letter = reference[ptInt[i + j]].toCharArray();
				blockKey[j+1] = rightShift(letter);
			}

			key = key + String.valueOf(convertToChar(blockKey));
			antikey = antikey + String.valueOf(convertToAntiChar(blockKey));

		}
		encryptLevel2();
	}

	public static char rightShift(char[] letter){
		char c = letter[4];
		for(int i=3;i>=0;i--){
			letter[i+1] = letter[i];
		}
		letter[0] = '0';
		ct = ct + String.valueOf(convertToChar(letter));
		return c;
	}

	public static void encryptLevel2(){
		if(ct.length() % antikey.length() != 0){
			int compensate = antikey.length() - (ct.length() % antikey.length());
			for(int i=0;i<compensate;i++){
				ct = ct + "x";
			}
		}

		char[] ptArray = ct.toCharArray();
		char[] antikeyArray = antikey.toCharArray();		

		for(int i=0;i<ct.length();i += antikey.length()){
			for(int j=0;j<antikey.length();j++){
				ptArray[i+j] = (char)(((((int)ptArray[i+j] - (int)'a') + ((int)antikeyArray[j] - (int)'a')) % 26) + 'A');
			}
		}

		ct2 = String.valueOf(ptArray);
	}

	public static void displayCT(){
		System.out.println();
		System.out.println(" CT : " + ct2);
		System.out.println();
		System.out.println(" KEY : " + antikey);
		System.out.println();
	}

	public static char convertToChar(char[] bKey){
		int sum = 0;
		for(int i=0;i<5;i++){
			if(bKey[i] == '1'){
				sum = sum + (int)Math.pow(2,4-i);
			}
		}
		return (char)(sum + (int)'a');
	}

	public static char convertToAntiChar(char[] bKey){
		int sum = 0;
		for(int i=0;i<5;i++){
			if(bKey[i] == '1'){
				sum = sum + (int)Math.pow(2,4-i);
			}
		}
		sum = 26 - sum;
		return (char)(sum + (int)'a');
	}

	public static void decryptMessage(){

		char[] ctArray = ct2.toCharArray();
		char[] antikeyArray = antikey.toCharArray();
		char[] keyArray = new char[antikey.length()];
		for(int i=0;i<antikey.length();i++){
			keyArray[i] = (char)((26 - ((int)antikeyArray[i] - (int)'a')) + (int)'a');
		}
		key = String.valueOf(keyArray);

		for(int i=0;i<ct2.length();i += key.length()){
			for(int j=0;j<key.length();j++){
				ctArray[i+j] = (char)(((((int)ctArray[i+j] - (int)'A') + ((int)keyArray[j] - (int)'a')) % 26) + 'a');
			}
		}

		ct = String.valueOf(ctArray);
		ct = ct.replaceAll("x","");

		decryptLevel2();
	}

	public static void decryptLevel2(){
		char[] ctArray = ct.toCharArray();
		int[] ctInt = new int[ct.length()];
		char[] keyArray = key.toCharArray();
		int[] keyInt = new int[key.length()];

		for(int i=0;i<key.length();i++){
			keyInt[i] = (int)((int)keyArray[i] - (int)'a');
		}

		for(int i=0;i<ct.length();i += 4){
			for(int j=0;j<4;j++){
				ctInt[i+j] = (int)((int)ctArray[i + j] - (int)'a');
				char[] letter = reference[ctInt[i + j]].toCharArray();
				char add = reference[keyInt[(i+j)/4]].charAt(j+1);
				leftShift(letter,add);
			}
		}
	}

	public static void leftShift(char[] letter,char add){
		for(int i=0;i<4;i++){
			letter[i] = letter[i+1];
		}
		letter[4] = add;
		String toAdd = String.valueOf(convertToChar(letter));
		if(!toAdd.equals("{")){
			newpt = newpt + toAdd;
		}
	}

	public static void displayDeciphered(){
		System.out.println();
		System.out.println();
		System.out.println(" DECIPHERED TEXT : " + newpt);
		System.out.println();
		System.out.println();
	}
}

/*
EXAMPLE : 1 : 

 Right Shift Key Cipher 
========================

Enter the Plain-Text : helloworld


 CT : SBTUGZWHTQMB

 KEY : pzo


 Do you want to Decrypt the above message ?(Y/n) : 



 DECIPHERED TEXT : helloworld


--------------------END------------------------


EXAMPLE : 2 : 


 Right Shift Key Cipher 
========================

Enter the Plain-Text : weneedimmideatehelp


 CT : JYEXWZAEBYZYYEWBYDCH

 KEY : ywyvu


 Do you want to Decrypt the above message ?(Y/n) : y



 DECIPHERED TEXT : weneedimmideatehelp


--------------------END------------------------


EXAMPLE : 3 : (To check long inputs)

 Right Shift Key Cipher 
========================

Enter the Plain-Text : hello we need help here as soon as possible contact emergency services this is just to check if all long inputs are processed or not


 CT : SBCYDLTGQWXZUZGVYWUVWGDWGYWWGGCAAWCPBCFSVIUCQAYPDXBJAVZDYVFJUEXYFACDIBDPVXOCARADSUGDWAGYKXDWEUBHZXQBEPYDXGFW

 KEY : pzxtw{r{ouwwsuzswosvnxwp{yn


 Do you want to Decrypt the above message ?(Y/n) : y



 DECIPHERED TEXT : helloweneedhelphereassoonaspossiblecontactemergencyservicesthisisjusttocheckifalllonginputsareprocessedornot


--------------------END------------------------

*/