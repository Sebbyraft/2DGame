package loader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class StringLoader {

	public static String loadString(String path) {
		FileReader f;
		StringBuilder sb = new StringBuilder();
		BufferedReader re ;
		try {
			f = new FileReader(path);
			re = new BufferedReader(f);
			String line = null;
			
			while((line = re.readLine())!=null) {
				sb.append(line);
				System.out.println(line.toString());
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
	
	public static ArrayList<String> loadStrings(String path) {
		ArrayList<String> strings = new ArrayList<String>();
		FileReader f;
		StringBuilder sb = new StringBuilder();
		BufferedReader re ;
		try {
			f = new FileReader(path);
			re = new BufferedReader(f);
			String line = null;
			
			while((line = re.readLine())!=null) {
				sb.append(line);
				strings.add(line.toString());
			}
			re.close();
		} catch (FileNotFoundException e1) {
			System.err.println("Could not find file");
			e1.printStackTrace();
		} catch (IOException e1) {
			System.err.println("Could not load file");
			e1.printStackTrace();
		}
		return strings;
	}
	
}
