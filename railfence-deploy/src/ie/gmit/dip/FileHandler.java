package ie.gmit.dip;

import java.io.*;
import java.net.URL;

public class FileHandler {

	private RailFenceCypher cypher;
	private FileWriter fw;

	public FileHandler(RailFenceCypher c) throws IOException {
	  cypher = c;


	parse(new URL("HappyPrince-Wilde.txt").openStream(), true);
  
  
  
  }

	public void parse(InputStream in, boolean encrypt) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));

		String line;

		while ((line = br.readLine()) != null) {
			String text = encrypt ? cypher.encrypt(line) : cypher.decrypt(line);
			fw.write(text);
		}

		br.close();
		fw.close();

	}
}
	// My decryption attempt

	/*
	 * matrix=new char[key][cipherText.length()];
	 * 
	 * String decryptedText = "";
	 * 
	 * int totalRows = key; int totalColumns = cipherText.length();
	 * 
	 * int row = 0;
	 * 
	 * // boolean value to tell us to move down or up in a fence //boolean shift =
	 * false;
	 * 
	 * // Moving to each column and then moving to particular row cell to place the
	 * // character for( int column = 0;column<totalColumns;column++) {
	 * 
	 * // Find out if it going up or down if (row == 0 || row == key - 1) {
	 * 
	 * shift = !shift; }
	 * 
	 * matrix[row][column]=cipherText.charAt(column);
	 * 
	 * // Time for the shift and adjust the row
	 * 
	 * // Going down the fence, so increasing the row if(shift==true) {
	 * 
	 * row++;
	 * 
	 * }else { row--; }
	 * 
	 * // Going up the row, so decrease the row
	 * 
	 * // end of for loop for encrypt...
	 * 
	 * for( int i = 0;i<totalRows;i++) { for (int j = 0; j < totalColumns; j++) {
	 * 
	 * if (matrix[i][j] != 0) { decryptedText += matrix[i][j]; }
	 * 
	 * }
	 * 
	 * return decryptedText;
	 * 
	 * } }
	 */



