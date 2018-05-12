package week2;
import java.util.*;
import edu.duke.*;

public class DNAProblem {
	 private HashMap<String, Integer> codonMap;

	    /* Write a constructor to initialize the HashMap variable. */
	    public DNAProblem() {
	        this.codonMap = new HashMap<String, Integer>();
	    }

	    /* Write a void method named buildCodonMap that has two parameters, an int named start and a String named dna. This
	    * method will build a new map of codons mapped to their counts from the string dna with the reading frame with the
	    * position start (a value of 0, 1, or 2). You will call this method several times, so make sure your map is empty
	    * before building it.
	    */
	    private void buildCodonMap(int start, String dna) {
	        this.codonMap.clear();
	        for (int i = start; i < dna.length() - 3; i += 3) {
	            if (dna.substring(i, i + 3).matches("\\w{3}")) {
	                if (!this.codonMap.containsKey(dna.substring(i, i + 3))) {
	                    this.codonMap.put(dna.substring(i, i + 3), 1);
	                } else {
	                    this.codonMap.put(dna.substring(i, i + 3), this.codonMap.get(dna.substring(i, i + 3)) + 1);
	                }
	            }
	        }
	    }

	    /* Write a method named getMostCommonCodon that has no parameters. This method returns a String, the codon in a
	    * reading frame that has the largest count. If there are several such codons, return any one of them. This method
	    * assumes the HashMap of codons to counts has already been built.
	    */
	    private String getMostCommonCodon() {
	        if (!this.codonMap.isEmpty()) {
	            int max = 0;
	            String cdn = "";
	            for (String codon : this.codonMap.keySet()) {
	                if (max < this.codonMap.get(codon)) {
	                    max = this.codonMap.get(codon);
	                    cdn = codon;
	                }
	            }
	            return cdn;
	            //return this.codonMap.toString();
	        }
	        return "NONE";
	    }

	    /* Write a void method named printCodonCounts that has two int parameters, start and end. This method prints all the
	    * codons in the HashMap along with their counts if their count is between start and end, inclusive.
	    */
	    private void printCodonCounts(int start, int end) {
	        System.out.println("Counts of codons between " + start + " and " + end + " inclusive are:");
	        for (String codon : this.codonMap.keySet()) {
	            if (this.codonMap.get(codon) >= start && this.codonMap.get(codon) <= end) {
	                System.out.println(codon + "\t" + this.codonMap.get(codon));
	            }
	        }
	    }

	    /* Write a tester method that prompts the user for a file that contains a DNA strand (could be upper or lower case
	    * letters in the file, convert them all to uppercase, since case should not matter). Then for each of the three
	    * possible reading frames, this method builds a HashMap of codons to their number of occurrences in the DNA strand,
	    * prints the total number of unique codons in the reading frame, prints the most common codon and its count, and
	    * prints the codons and their number of occurrences for those codons whose number of occurrences in this reading
	    * frame are between two numbers inclusive.
	    */
	    public void tester(int start, int end) {
	        //prompts the user for a file that contains a DNA strand
	        FileResource fr = new FileResource();
	        //convert them all to uppercase
	        String dna = fr.asString().toUpperCase();
	        //then for each of the three possible reading frames
	        for (int i = 0; i < 3; i++) {
	            //this method builds a HashMap of codons to their number of occurrences in the DNA strand
	            this.buildCodonMap(i, dna);
	            //prints the total number of unique codons in the reading frame
	            System.out.println(
	                    "Reading frame starting with " + i + " results in " + this.codonMap.size() + " unique codons"
	            );
	            //prints the most common codon and its count
	            System.out.println(
	                    "and most common codon is " + this.getMostCommonCodon()
	                            + " with count " + this.codonMap.get(this.getMostCommonCodon())
	            );
	            // prints the codons and their number of occurrences for those codons whose number of occurrences in this
	            //reading frame are between two numbers inclusive
	            printCodonCounts(start, end);
	            this.codonMap.clear();
	        }

	    }
}
