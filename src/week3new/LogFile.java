package week3new;
import java.util.*;
import java.io.*;
import edu.duke.*;
public class LogFile {
	 private ArrayList<LogEntry> logEntries;
	    private String logString;
	    private ArrayList<String> months;

	    public LogFile(FileResource fileResource) {
	        setMonths();
	        logEntries = new ArrayList<>();
	        for (String s : fileResource.lines()) {
	            logEntries.add(parseEntry(s));
	        }
	    }

	    public LogFile(URLResource urlResource) {
	        setMonths();
	        logEntries = new ArrayList<>();
	        for (String s : urlResource.lines()) {
	            logEntries.add(parseEntry(s));
	        }
	    }

	    private LogEntry parseEntry(String logString) {
	        this.logString = logString;
	        String str[] = this.logString.split(" ");
	        LogEntry logEntry = new LogEntry(str[0], readDate(),
	                logString.replaceFirst("^.* \"", "").replaceFirst("\" .*$", ""),
	                Integer.parseInt(str[8]), Integer.parseInt(str[9]));
	        return logEntry;
	    }

	    private Date readDate() {
	        String str = this.logString.replaceFirst("^.*\\[", "").replaceFirst("].*$", "");
	        int year = Integer.parseInt(str.substring(7, 11));
	        int month = months.indexOf(str.substring(3, 6));
	        int date = Integer.parseInt(str.substring(0, 2));
	        int hrs = Integer.parseInt(str.substring(12, 14));
	        int min = Integer.parseInt(str.substring(15, 17));
	        int sec = Integer.parseInt(str.substring(18, 20));
	        Date d = new Date(year - 1900, month, date, hrs, min, sec);
	        return d;
	    }

	    private void setMonths() {
	        String months[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
	        this.months = new ArrayList<>();
	        for (String month : months) {
	            this.months.add(month);
	        }
	    }

	    public ArrayList<LogEntry> getLogEntries() {
	        return this.logEntries;
	    }
}
