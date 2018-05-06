package week1;
import edu.duke.*;
public class WordLengths {
	 public static void countWordLengths(FileResource resource, int[] counts) {
	        //read in the words from resource
	        
	        //count the number of words of each length for all the words in resource
	        //storing these counts in the array counts.
	        for(String word : resource.words()){
	            word = word.toLowerCase();
	            int length = word.length();
	            if (Character.isLetter(word.charAt(0)) == false) {
	                length--;
	            }
	            if (Character.isLetter(word.charAt(word.length()-1)) == false) {
	                length--;
	            }
	            if (length >= 30) {
	                length = 30;
	            }
	            if (length >= counts.length) {
	                length = counts.length - 1;
	            }
	            if (length > 0) {
	                counts[length] += 1;
	            }
	        }
	    }
	    
	    public static int indexOfMax(int[] values) {
	        int index = 0;
	        int largestValue = values[0];
	        for (int k = 0; k< values.length; k++){
	            if (values[k] > largestValue) {
	                index = k;
	                largestValue = values[k];
	            }
	        }
	        return index;
	    }
	    
	    public static void testCountWordLengths() {
	        FileResource resource = new FileResource();
	        
	        int[] counts = new int[31]; // to store numbers [0,1,...,30]
	        
	        countWordLengths(resource, counts);
	        
	        //print the number of words of each length. Test it on the small file smallHamlet.txt shown below.
	        for(int k=0; k < counts.length; k++){
	            System.out.println("Length " + k + "\t" + counts[k]);
	        }

	        // using file smallHamlet.txt should return 3.
	        System.out.println("Most common word length: " + indexOfMax(counts));
	    }
	    public static void main(String args[]) {
	    	testCountWordLengths();
	    }
	

}
