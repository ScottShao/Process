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
import java.util.Map.Entry;

public class Mapping {
	public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException, IOException {
		Map<String, Integer> entityToId = new HashMap<>();
		Map<String, Integer> predicatesToId = new HashMap<>();
		int entityId = 0;
		int predicatesId = 0;
		int num = 0;
		try (BufferedReader br = new BufferedReader(new FileReader("g.txt")); 
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream("final.txt"), "utf-8"))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	if (line.startsWith("#")) continue;
//		    	System.out.println(line);
		    	String[] words = line.split(" ");
		    	String out = "";
		    	int id;
		    	if (entityToId.containsKey(words[0])) {
		    		id = entityToId.get(words[0]);
		    	} else {
		    		id = entityId++;
		    		entityToId.put(words[0], id);
		    	}
		    	out += id + " ";
		    	
		    	if (predicatesToId.containsKey(words[1])) {
		    		id = predicatesToId.get(words[1]);
		    	} else {
		    		id = predicatesId++;
		    		predicatesToId.put(words[1], id);
		    	}
		    	out += id + " ";
//		    	System.out.println(line);
		    	String temp = line.substring(words[0].length() + words[1].length() + 2);
		    	if (entityToId.containsKey(temp)) {
		    		id = entityToId.get(temp);
		    	} else {
		    		id = entityId++;
		    		entityToId.put(temp, id);
		    	}
		    	out += id;
		    	writer.write(out);
		    	writer.newLine();
//		    	System.out.println(line);
//		    	System.out.println(out);
//		    	num++;
//		    	if (num > 50) {
//		    		break;
//		    	}
		    }
		} 
		
		
		
		BufferedWriter entityWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("entityMapping.txt"), "utf-8"));
		BufferedWriter predicatesWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("predicatesMapping.txt"), "utf-8"));
		
		for (Entry<String, Integer> en : entityToId.entrySet()) {
			entityWriter.write(en.getKey() + " " + en.getValue());
			entityWriter.newLine();
		}
		entityWriter.flush();
		entityWriter.close();
		
		for (Entry<String, Integer> en : predicatesToId.entrySet()) {
			predicatesWriter.write(en.getKey() + " " + en.getValue());
			predicatesWriter.newLine();
		}
		predicatesWriter.flush();
		predicatesWriter.close();
	}
}
