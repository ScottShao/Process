import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class SwitchPlace {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Map<String, Integer> entityToId = new HashMap<>();
		Map<String, Integer> predicatesToId = new HashMap<>();
		int entityId = 0;
		int predicatesId = 0;
		int num = 0;
		try (BufferedReader br = new BufferedReader(new FileReader("final.txt")); 
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream("graph.txt"), "utf-8"))) {
		    String line = null;
		    while ((line = br.readLine()) != null) {
		    	String[] words = line.split(" ");
		    	writer.write(words[0] + " " + words[2] + " " + words[1]);
		    	writer.newLine();
		    }
		    br.close();
		    writer.close();
		} 
		
	}

}
