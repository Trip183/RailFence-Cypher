package ie.gmit.dip;

import java.io.IOException;
import java.util.Scanner;

public class CipherMenu {
	private Scanner s;
	private boolean keepRunning = true;
	
	private String fileName;
	
	private int key = -1;
	
	private int offset = 0;

	public CipherMenu() {
		s = new Scanner(System.in);
	}

	public void start() {
		while (keepRunning) {
			showOptions();
			int choice = Integer.parseInt(s.next());

			if (choice == 1) {
				getFilePath();
			} else if (choice == 2) {
				getKey();
			}else if (choice == 3) {
				getOffset();
			} else if (choice == 4) {
				encryption();
			} else if (choice == 5) {
				decryption();
			} else if (choice == 6) {
				System.out.println("[INFO] Shutting Down...please wait...");
				keepRunning = false;

			} else {
				System.out.println("[ERROR] Invalid Input");
			}
		}

	}

	private void getFilePath() {
		System.out.println("Enter text to be encrypted/decrypted");
		fileName = s.next();
	}

	
	private void getKey() {
		System.out.println("Enter a key");
		key = s.nextInt();
		// validate for negative	
		}
	
	private void getOffset() {
		System.out.println("Enter a Offset");
		offset = s.nextInt();
	}

	private void encryption(){
		if(fileName != null && key > -1)
		{
			RailFenceCypher cipher = new RailFenceCypher(key, offset);
			try {
				cipher.encryptFile(fileName);
			} catch (IOException e) {
				System.out.println("Error in reading file/Url");
				showOptions();
			}
			fileName = null;
			key = -1;
		}
		else
		{
			System.out.println("Please enter a file and key first");
		}
	}
	
	private void decryption(){
		if(fileName != null && key > -1)
		{
			RailFenceCypher cipher = new RailFenceCypher(key, offset);
			try {
				cipher.decryptFile(fileName);
			} catch (IOException e) {
				System.out.println("Error in reading file/Url");
				showOptions();
			}
			fileName = null;
			key = -1;
		}
		else
		{
			System.out.println("Please enter a file and key first");
		}
	}

	private void showOptions() {
		System.out.println("################################");
		System.out.println("#      RailFence Cipher        #");
		System.out.println("################################");
		System.out.println("(1) Select File or Url");
		System.out.println("(2) Enter Rail Fence Key");
		System.out.println("(3) Enter Rail Fence offset");
		System.out.println("(4) Encrypt");
		System.out.println("(5) Decrypt");
		System.out.println("(6) Quit");
		System.out.println("select an option [1-6]>");
	}	
}
