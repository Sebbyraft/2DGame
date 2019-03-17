package loader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class StringLoader {

	public static String loadString(String path) {
		FileReader f;
		StringBuilder sb = new StringBuilder();
		BufferedReader re ;
		try {
			f = new FileReader(path);
			re = new BufferedReader(f);
			String line = null;
			String ls = System.getProperty("line.separator");
			
			while((line = re.readLine())!=null) {
				sb.append(line);
				sb.append(ls);
			}
			re.close();
		} catch (FileNotFoundException e1) {
			System.err.println("Could not find file");
			e1.printStackTrace();
		} catch (IOException e1) {
			System.err.println("Could not load file");
			e1.printStackTrace();
		}
		return sb.toString();
	}
	
}
