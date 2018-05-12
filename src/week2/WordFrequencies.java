package week2;
import java.util.*;
import edu.duke.*;
import java.io.*;
public class WordFrequencies {
	 /* kth position in myFreqs represents # of times kth word in myWords occurs in file */
    private static ArrayList<String> myWords;
    private static ArrayList<Integer> myFreqs;

    public WordFrequencies() {
        myWords = new ArrayList<>();
        myFreqs = new ArrayList<>();
    }


    /* Clear myWords and myFreqs with .clear()
     * Select file, iterate over every word in file
     * if word is unique, place in myWords.
     * Each time word in  myWords is encountered, myFreq(k) is
     * incremented by 1.
     *  */
    
    
    public static void findUnique() {
    	/*if(myWords == null)
    		throw new NullPointerException();
    	if(myFreqs == null)
    		throw new NullPointerException();*/
    	myWords.clear();
        myFreqs.clear();
    	
        FileResource file = new FileResource();
        for (String word : file.words()) {
            int index = myWords.indexOf(word.toLowerCase());
            if (index != -1) {                          //if item occurs in myWords
                int value = myFreqs.get(index);
                myFreqs.add(index, value+1);    //increment value at index by 1
            } else if (index == -1) {
                myWords.add(word.toLowerCase());        //assuming myWords and MyFreqs are same size
                myFreqs.add(1);
            }
        }
    }

    /* Returns index of location of largest value in myFreqs.
     * If tie occurs then first occurrence is returned.
     */
    static int findIndexOfMax() {
        int max = 0;
        int indexOfMax = 0;
        for (int i = 0; i < myFreqs.size(); i++) {
            int currentValue = myFreqs.get(i);
            if (currentValue > max) {
                max = currentValue;
                indexOfMax = i;
            }
        }
        return indexOfMax;
    }

    /* Call findUnique(), print out number of unique words and
     * the number of times they occur from myFreqs.
     * Will also determine and print word that occurs most often
     * in selected file and how many times it occurs.
     * */
    public static void tester() {
        findUnique();
        int size = myWords.size();
        int indexOfMax = findIndexOfMax();
        System.out.println("Number of unique words: " + size);
        /*for (int i = 0; i < size-1; i++) {
            System.out.println(myFreqs.get(i) + " " + myWords.get(i));
        }*/
        System.out.println("The word that occurs most often and its count are: "
                            + myWords.get(indexOfMax) + " " + myFreqs.get(indexOfMax));
    }

    public static void main(String args[]) {
    	tester();
    }
}
