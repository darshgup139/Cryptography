//Vernam Cipher
//Rohit G Siddheshwar

import java.util.*;

class Vernam{
	public static void main(String[] args){
		String plainText;
		char[] ptChar;
		System.out.println();
		System.out.println();
		System.out.print("Enter Plain Text : ");
		Scanner sc = new Scanner(System.in);
		plainText = sc.nextLine();
		System.out.println();
		plainText = plainText.replaceAll(" ","");
		ptChar = plainText.toCharArray();
		int[] key = new int[ptChar.length];
		char[] op = new char[ptChar.length];
		System.out.println();
		System.out.print("Cipher Text : ");
		for(int i = 0;i<ptChar.length;i++){
			key[i] = ((int)(Math.random() * 100)) % 26;
			op[i] = (char)(((ptChar[i] - 'a' + key[i]) % 26) + 'a');
			System.out.print(op[i]);
		}

		System.out.println();
		System.out.println();
		System.out.print("Key : ");

		for(int i=0;i<ptChar.length;i++){
			System.out.print(key[i] + " ");
		}

		System.out.println();
		
	}
}

/*


Enter Plain Text : hellosir


Cipher Text : ahotkrvp

Key : 19 3 3 8 22 25 13 24 




Enter Plain Text : i am not a racist black boy


Cipher Text : hddwhiiatgwzipsmssxzh

Key : 25 3 17 9 19 15 8 9 19 4 14 7 15 14 7 12 16 8 22 11 9 


*/