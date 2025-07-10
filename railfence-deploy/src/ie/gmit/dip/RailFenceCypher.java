package ie.gmit.dip;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
//import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


public class RailFenceCypher {

	private char[][] matrix;
	public int offset;
	public Scanner scanner;
	private int key;
	

	public RailFenceCypher(int key, int offset) {
		this.key = key;
		this.offset = offset;

	}

	public void encryptFile(String filePath) throws IOException {
		
		//String encryptedFileName = filePath.endsWith(".txt") ? filePath.subSequence(0, filePath.length()- 4) + "-Encrypted.txt" : "./src/EncryptedFiles/" + filePath + "-Encrypted.txt";
		
		// File write object
		
		if (filePath.endsWith(".txt")) {
			File textFile = new File(filePath);			

			Scanner in = new Scanner(textFile);

			while (in.hasNextLine()) {
				String encr = encrypt(in.nextLine());
				System.out.println(encr);
				// write to the encr file
			}
			in.close();
		} else {
			URL url = new URL(filePath);
			InputStream in = url.openStream();			
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line = null;

			while ((line = br.readLine()) != null) {
				String encrText = encrypt(line);
				System.out.println(encrText);
				// write to the encr file
				}
			br.close();	// Leaving out this line causes memory leaks!
		}
		//show message with encrypted file name 
	}
	
	public void decryptFile(String filePath) throws IOException {
		
		if (filePath.endsWith(".txt")) {
			File textFile = new File(filePath);

			Scanner in = new Scanner(textFile);

			while (in.hasNextLine()) {
				String decr = decrypt(in.nextLine());
				System.out.println(decr);
			}
			in.close();
		} else {
			URL url = new URL(filePath);
			InputStream in = url.openStream();			
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line = null;

			while ((line = br.readLine()) != null) {
				String decrText = decrypt(line);
				System.out.println(decrText);
				}
			br.close();	// Leaving out this line causes memory leaks!
		}
	}

	public String encrypt(String plainText) {

		matrix = new char[key][plainText.length()];

		String encryptedText = "";

		int totalRows = key;
		int totalColumns = plainText.length();

		int row = 0 + offset;

		// boolean value to tell us to move down or up in a fence
		boolean shift = false; // up

		if (offset > 0) {
			shift = !shift;
		}

		// Moving to each column and then moving to particular row cell to place the
		// character
		for (int column = 0; column < totalColumns; column++) {

			// Find out if it going up or down
			if (row == 0 || row == key - 1) {

				shift = !shift;
			}

			matrix[row][column] = plainText.charAt(column);

			// Time for the shift and adjust the row

			// Going down the fence, so increasing the row
			if (shift == true) {

				row++;

			} else {

				// Going up the row, so decrease the row
				row--;
			}

		} // end of for loop for encrypt...

		for (int i = 0; i < totalRows; i++) {

			for (int j = 0; j < totalColumns; j++) {

				if (matrix[i][j] != 0) {
					encryptedText += matrix[i][j];
				}
			}

		}

		return encryptedText;

	}

	public String decrypt(String cipherText) {
		
		char[][] rail = new char[key][cipherText.length()];
		// matrix
		for (int i = 0; i < key; i++) {
			for (int j = 0; j < cipherText.length(); j++) {
				rail[i][j] = '*';
			}
		} // for


		// putting letters in the matrix in zig-zag
		int row = 0;
		int check = 0;
		for (int i = 0; i < cipherText.length(); i++) {
			if (check == 0) {
				rail[row][i] = cipherText.charAt(i);
				row++;
				if (row == key) {
					check = 1;
					row--;
				}
			} else if (check == 1) {
				row--;
				rail[row][i] = cipherText.charAt(i);
				if (row == 0) {
					check = 0;
					row = 1;
				}
			} // if-else
		} // for

		// changing order of rails
		int ordr = 0;
		for (int i = 0; i < key; i++) {
			for (int j = 0; j < cipherText.length(); j++) {
				String temp = rail[i][j] + "";
				if (temp.matches("\\*")) {
					// skipping in case of '*'
					continue;
				} else {
					// adding cipher letters one by one diagonally
					rail[i][j] = cipherText.charAt(ordr);
					ordr++;
				} // if-else
			} // inner for
		} // for

		
		String decrypText = "";
		check = 0;
		row = 0;
		// converting rails back into a single line message
		for (int i = 0; i < cipherText.length(); i++) {
			if (check == 0) {
				decrypText += rail[row][i];
				row++;
				if (row == key) {
					check = 1;
					row--;
				}
			} else if (check == 1) {
				row--;
				decrypText += rail[row][i];
				if (row == 0) {
					check = 0;
					row = 1;
				}
			} // if-else
		} // for

		//System.out.println("Decrypted Text: " + decrypText);
		return decrypText;
	}

	
	  public static void main(String[] args) throws FileNotFoundException {
	  
	  System.out.println("Please Enter a File or URL:");
	  
	  Scanner scanner = new Scanner(System.in);
	  
	  String input1 = scanner.nextLine();
	  
	  System.out.println("Please Enter a Key:");
	  
	  String input2 = scanner.nextLine();
	  
	  System.out.println("Please Enter offset(Range is between 0 and key): ");
	  
	  String input3 = scanner.nextLine();
	  
	  boolean isEnc = false;
	  
	  System.out.println("Please Choose: 1)Encryption or 2)Decryption");
	  
	  String input4 = scanner.nextLine();
	  
	  if (input4.equalsIgnoreCase("1")) { 
		  
		  isEnc = true; 
		  
		  } else { 
			  isEnc = false;
		  
	}
	  
	  // scanner to get the input 
	  RailFenceCypher rf = new RailFenceCypher(Integer.valueOf(input2), Integer.valueOf(input3));
	  
	  if (isEnc) { 
		  String encp = rf.encrypt(input1);
		  
		  System.out.println("Encrypted Text: " + encp); } 
	  
	  else {
	  
			  String decr = rf.decrypt(input1); 
			  System.out.println("Decrypted Text: " + decr);
	  
	  } 
	  scanner.close(); 
	 }
	 
}
