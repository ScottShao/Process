import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

public class Sampling {
	public static void main(String[] args) throws IOException{
		Map<Integer, Map<Integer, Integer>> outgoingEdges = new HashMap<>();
		Map<Integer, Map<Integer, Integer>> incomingEdges = new HashMap<>();
		BufferedReader br = null;
		try {
				br = new BufferedReader(new FileReader("final.txt"));
				String line;
				int lineNum = 0;
				while ((line = br.readLine()) != null) {
					String[] words = line.split(" ");
					int src = Integer.parseInt(words[0]);
					int dst = Integer.parseInt(words[2]);
					int label = Integer.parseInt(words[1]);
					Map<Integer, Integer> pair = outgoingEdges.get(src);
					if (pair == null) {
						pair = new HashMap<>();
						outgoingEdges.put(src, pair);
					} 
					pair.put(dst, label);
					
					pair = incomingEdges.get(dst);
					if (pair == null) {
						pair = new HashMap<>();
						incomingEdges.put(dst, pair);
					}
					pair.put(src, label);
				}
				lineNum++;
				if (lineNum % 10000 == 0) {
					System.out.println("Processing line num:" + lineNum);
				}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Random rnd = new Random();
		System.out.println(outgoingEdges.size());
		int startingNode = rnd.nextInt(outgoingEdges.size());
		int MAX_OUT_DEGREE = 5;
		int MAX_IN_DEGREE = 5;
		int MAX_EDGE_NUM = 10;
		int edgeNum = 0;
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter("sample.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Queue<Integer> queue = new LinkedList<>();
		queue.add(startingNode);
		boolean finished = false;
		while (!queue.isEmpty()) {
			int id = queue.poll();
			Map<Integer, Integer> edges = outgoingEdges.get(id);
			int index = 0;
			for (Entry<Integer, Integer> en : edges.entrySet()) {
				if (index++ >= MAX_OUT_DEGREE) break;
				if (edgeNum++ > MAX_EDGE_NUM) {
					finished = true;
					break;
				}
				System.out.println("writing edge " + edgeNum);
				queue.add(en.getKey());
				bw.write(id + " " + en.getKey() + " " + en.getValue());
				bw.newLine();
			}
			if (finished) break;
			edges = incomingEdges.get(id);
			index = 0;
			for (Entry<Integer, Integer> en : edges.entrySet()) {
				if (index++ >= MAX_IN_DEGREE) break;
				if (edgeNum++ > MAX_EDGE_NUM) {
					finished = true;
					break;
				}
				System.out.println("writing edge " + edgeNum);
				queue.add(en.getKey());
				bw.write(id + " " + en.getKey() + " " + en.getValue());
				bw.newLine();
			}
			if (finished) break;
		}
		bw.flush();
		bw.close();
	}
}
