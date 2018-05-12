package week2;
import edu.duke.*;
import org.apache.*;
import org.apache.commons.*;
import java.util.*;
import java.lang.*;

public class GlabLib {
	private static ArrayList<String> adjectiveList;
	private static ArrayList<String> nounList;
	private static ArrayList<String> colorList;
	private static ArrayList<String> countryList;
	private static ArrayList<String> nameList;
	private static ArrayList<String> animalList;
	private static ArrayList<String> timeList;
	private static ArrayList<String> verbList;
	private static ArrayList<String> fruitList;
	private static ArrayList<String> wordsAlreadyUsed;
	
	private static Random myRandom;
	
	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "/Users/bocanhu/eclipse-workspace/CourseraOOD2/src/week2/GladLibData";
	
	public static void main(String[] args) {
		GlabLib gladlib = new GlabLib();
		gladlib.makeStory();
	}
	
	public void GladLib(){
		initializeFromSource(dataSourceDirectory);
		myRandom = new Random();
	}
	
	public void GladLib(String source){
		initializeFromSource(source);
		myRandom = new Random();
	}
	
	private void initializeFromSource(String source) {
		adjectiveList= readIt(source+"/adjective.txt");	
		nounList = readIt(source+"/noun.txt");
		colorList = readIt(source+"/color.txt");
		countryList = readIt(source+"/country.txt");
		nameList = readIt(source+"/name.txt");		
		animalList = readIt(source+"/animal.txt");
		timeList = readIt(source+"/timeframe.txt");		
		verbList = readIt(source+"/verb.txt");
		fruitList = readIt(source+"/fruit.txt");
		wordsAlreadyUsed = new ArrayList<>();
	}
	
	private static String randomFrom(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	
	private static String getSubstitute(String label) {
		if (label.equals("country")) {
			return randomFrom(countryList);
		}
		if (label.equals("color")){
			return randomFrom(colorList);
		}
		if (label.equals("noun")){
			return randomFrom(nounList);
		}
		if (label.equals("name")){
			return randomFrom(nameList);
		}
		if (label.equals("adjective")){
			return randomFrom(adjectiveList);
		}
		if (label.equals("animal")){
			return randomFrom(animalList);
		}
		if (label.equals("timeframe")){
			return randomFrom(timeList);
		}
		if (label.equals("number")){
			return ""+myRandom.nextInt(50)+5;
		}
		if (label.equals("verb")) {
			return randomFrom(verbList);
		}
		if (label.equals("fruit")) {
			return randomFrom(fruitList);
		}
		return "**UNKNOWN**";
	}
	
	private static String processWord(String w){
		int first = w.indexOf("<");
		int last = w.indexOf(">",first);
		if (first == -1 || last == -1){
			return w;
		}
		String prefix = w.substring(0,first);
		String suffix = w.substring(last+1);
		String sub = getSubstitute(w.substring(first+1,last));
		if (wordIsAlready(sub)) {
			sub = getWordNotUsed(w, first, last, sub);
		}
		wordsAlreadyUsed.add(sub);
		return prefix+sub+suffix;
	}

	private static String getWordNotUsed(String w, int first, int last,
			String sub) {
		int index;
		do {
			sub = getSubstitute(w.substring(first+1,last));
			index = wordsAlreadyUsed.indexOf(sub);
		} while (index != -1);
		return sub;
	}
	private static boolean wordIsAlready(String sub){
		int index = wordsAlreadyUsed.indexOf(sub);
		return index != -1;
	}
	
	private static void printOut(String s, int lineWidth){
		int charsWritten = 0;
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w+" ");
			charsWritten += w.length() + 1;
		}
	}
	
	private static String fromTemplate(String source){
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}
	
	private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		return list;
	}
	
	public static void makeStory(){
	    System.out.println("\n");
		String story = fromTemplate(dataSourceDirectory +  "/madtemplate2.txt");
		printOut(story, 60);
	}
	
	    
	 
}
