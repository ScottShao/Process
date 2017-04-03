import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class extractFields {
	public static void main(String[] args) throws IOException {
		
//		String test = "<http://dbpedia.org/resource/Alabama> <http://xmlns.com/foaf/0.1/name> \"State of Alabama\"@en .";
//		String a = test.split(" ")[3];
//		System.out.println(a);
//		
		try (BufferedReader br = new BufferedReader(new FileReader("graph.txt")); 
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream("g.txt"), "utf-8"))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	if (line.startsWith("#")) continue;
////		    	System.out.println(line);
//		    	String[] words = line.split(" ");
////		    	System.out.println(line);
//		    	words[2] = line.substring(line.indexOf(words[2]));
//		    	String out = getBaseName(words[0]) + " "  + getBaseName(words[1]);
//		    	String temp = extractBaseName(words[2]);
//		    	if (temp == null || temp.trim().length() == 0) {
//		    		out = out + " " + words[2].trim();
//		    	} else {
//		    		out = out + " " + temp;
//		    	}
		    	String out = line.substring(0, line.lastIndexOf(".")).trim();
		    	if (out.contains("^^<")) {
		    		out = out.substring(0, out.lastIndexOf("^^<"));
		    	}
		    	if (out.endsWith("@en")) {
		    		out = out.substring(0, out.lastIndexOf("@en"));
		    	}
		    	
		    	if (out.endsWith("Edon, ") || out.endsWith("Edom, ")) {
		    		System.out.println(line);
		    	}
		    	
		    	writer.write(out);
		    	writer.newLine();
		    }
		}
	}
	private static String extractBaseName(String word) {
		if (word.contains("\"")) {
//			System.out.println(word + " " + word.indexOf("\"") + 1 + " " + word.lastIndexOf("\""));
			return word.substring(word.indexOf("\"") + 1, word.lastIndexOf("\""));
		} else {
			return getBaseName(word);
		}
	}
	private static String getBaseName(String url) {
		int lastIndex = url.lastIndexOf("/");
		int endIndex = url.lastIndexOf(">");
		if (lastIndex == -1 || endIndex == -1) {
//			System.err.println("error parsing:" + url);
			return null;
		} else {
			try {
				return url.substring(lastIndex + 1, endIndex); 
			} catch (StringIndexOutOfBoundsException e) {
				System.out.println(url);
				return null;
			}
		}
	}
}
