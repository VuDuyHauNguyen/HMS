package Boundary.Helpers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHelper {
	public static boolean saveFile(String path, String content) {
		try {
	    	//store to file
	    	Files.write(Paths.get(path), content.getBytes());
	    	
	    	//success 
	    	return true;
	    }catch(IOException ex) {
	    	//error message
	    	System.err.println("Cannot store process pattern!\n" + ex.getMessage());
	    	return false;
	    }
	}
	
	//get all content of a file as a String
	public static String readFile(String path) {
		String content = "";
		
		try {
			content = new String(Files.readAllBytes(Paths.get(path)));
		}catch(Exception ex) {
			//error message
	    	System.err.println("Cannot read file!\n" + ex.getMessage());
		}
		
		return content;
	}
}
