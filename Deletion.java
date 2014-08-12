import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.ArrayList;

public class Deletion {

	public static void main(String[] args) {
		if(args.length == 0) {
			System.out.println("Provide file to delete a list");
			return;
		}
		BufferedReader br = null;
 
		try {
 
			String sCurrentLine;
 
			br = new BufferedReader(new FileReader(args[0]));
 
			while ((sCurrentLine = br.readLine()) != null) {
				new File(sCurrentLine).delete();
			}
 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}

