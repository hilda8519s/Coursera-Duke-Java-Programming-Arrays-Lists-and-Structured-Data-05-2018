package week3new;
import edu.duke.*;
import java.io.*;
import java.util.*;

public class Test {
	 public static void main(String[] args) {
	        FileResource fileResource = new FileResource(); // 
	        LogFile logFile = new LogFile(fileResource);

	        
	        LogAnalyzer logAnalyzer = new LogAnalyzer(logFile);
	        logAnalyzer.printAll();
	        logAnalyzer.printUniqueIpAddresses();
	        //logAnalyzer.printAllHigherThanNum(400);

	        String someday = "Sep 27";
	        System.out.println("Unique visits on " + someday + ": " + logAnalyzer.countUniqueIPVisitsOnDay(someday));

	        int low = 200;
	        int high = 299;
	        System.out.println("Unique IPs within the range " + low + "-" + high + ": "
	                + logAnalyzer.countUniqueIPsInRange(low, high));

	        /** Counting Web-Site Visits */
	        System.out.println("/**** Counting Web-Site Visits ****/");
	        System.out.println("Most visits by ip: " + logAnalyzer.mostNumberVisitsByIP());
	        System.out.println("IPs with most visits: " + logAnalyzer.getiPsMostVisits());
	        System.out.println("Day with most visits: " + logAnalyzer.dayWithMostIPVisits());
	        String day = "Sep 30";
	        System.out.println("IPs with most visits on " + day + ": " + logAnalyzer.iPsWithMostVisitsOnDay(day));
	    }
}
