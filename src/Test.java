
public class Test {

	public static void main(String[] args) {
		String test = "<http://dbpedia.org/resource/The_Doors_(album)> <http://xmlns.com/foaf/0.1/name> \"The Doors\"@en .";
		System.out.println(test.substring(0, test.indexOf("@en .")));

	}

}
