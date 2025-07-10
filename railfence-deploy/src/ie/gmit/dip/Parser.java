/*
 * package ie.gmit.dip;
 * 
 * import java.io.*; //import java.net.*;
 * 
 * public class Parser {
 * 
 * 
 * public static void go(String fileName) throws Exception { try { URL url = new
 * URL("https://learnonline.gmit.ie/course/view.php?id=1695"); InputStream in =
 * url.openStream(); readText(in); // Read down through a text file (serial)
 * readText(new FileInputStream(new File("./src/HappyPrince-Wilde.txt")));
 * readStream(new FileInputStream(new File())); // Read a URL (serial)
 * 
 * } finally { // Any necessary clean-up should be do here!
 * System.out.println("Leaving go()"); } }
 * 
 * private void readText(InputStream in) throws Exception { BufferedReader br =
 * new BufferedReader(new InputStreamReader(in)); String line = null;
 * 
 * while ((line = br.readLine()) != null) { System.out.println(line); }
 * br.close(); // Leaving out this line causes memory leaks!
 * 
 * 
 * }
 * 
 * 
 * private void readStream(InputStream in) { try { int data = new
 * DataInputStream(in).read(); while (data != -1) { System.out.println(data);
 * data = in.read(); } } catch (IOException e) { e.printStackTrace(); } }
 * 
 * 
 * 
 * 
 * 
 * public static void main(String[] args) throws Exception { new Parser().go();
 * }
 * 
 * }
 */