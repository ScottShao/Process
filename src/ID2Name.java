import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class ID2Name {

	public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException, IOException {
		if (args.length < 1) {
			System.out.println("Not enough arguments, please retry");
			System.exit(1);
		}
		Map<Integer, String> entityMapping = loadMapping("entityMapping.txt");
		Map<Integer, String> predicateMapping = loadMapping("predicatesMapping.txt");
		
		try (BufferedReader br = new BufferedReader(new FileReader("args[0]"))) {
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] words = line.split(" ");
				System.out.println(entityMapping.get(Integer.parseInt(words[0])) + " " + entityMapping.get(Integer.parseInt(words[1])) + " " +
									predicateMapping.get(Integer.parseInt(words[2])));
			}
		} 
	}
	
	
	private static Map<Integer, String> loadMapping(String fileName) throws FileNotFoundException, IOException {
		Map<Integer, String> res = new HashMap<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] words = line.split(" ");
				int id = Integer.parseInt(words[words.length - 1]);
				res.put(id, line.substring(0, line.length() - words[words.length - 1].length()));
			}
		}
		
		return res;
	}

}
