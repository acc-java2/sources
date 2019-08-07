package edu.acc.java2.pix;

import java.io.*;
import java.util.*;
import java.util.regex.*;

/**
	In this solution, we'll read one of pixabay's photo pages' HTML source looking for photo URLs and then
	print the file names contained in those URLs in alphabetical order. To find these URLs, we're looking for
	img tags that have the srcset attribute. This attribute contains (generally and most probably exactly) two
	URLs for each image, one in each of two resolutions.
	
	We'll use a BufferedReader to find each line of the file as a String and we'll use fast indexOf() from the
	String class to find all the srcset attributes in each line. We'll use a powerful regex to extract the
	full URL of each image from inside the srcset attribute while isolating just the file name part at the end
	of each URL so we can add it to a list of Strings. 
	
	Lastly, we'll sort and print the list of file names, putting them on-screen in ascending alphabetical order.
*/

public class Pix {
	
	public static void main(String[] args) throws Exception {
		
		final Pattern patt = Pattern.compile("https://cdn.pixabay.com/photo(/\\d+)+/(.*?jpg)");
		/*
			The regex in this compile() call says to match:
			1. literal text beginning "https://cdn.pixabay.com/photo"
			2. a forward slash followed by one or more digits (/\\d+)
			3. the match from step 2 one or more times (+); step two is in parentheses because it
			   is the entire sequence of slash and digits that must repeat
			4. match the last forward slash in the string
			5. match all the characters up to the first occurrence of "jpg"; this step is in
			   parentheses because we want to be able to extract it as a subsequence from the match
		*/
		final String trigger = "srcset=\""; // this is the HTML attribute in an img tag where photo URLs are found
		
		List<String> fileNames = new ArrayList<>(); // start an empty array list for accruing filenames
		
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) { 
				int start = 0, end = 0; // index for beginning and end of srcset attribute
				while ((start = line.indexOf(trigger, start)) != -1) {
					start += trigger.length(); //index of first character inside opening quote of srcset attribute
					end = line.indexOf("\"", start); // find the closing quote of the attribute
					Matcher m = patt.matcher(line.substring(start, end)); // search only the attribute text
					while (m.find()) {// there will probably be two URLs in each srcset attribute
						fileNames.add(m.group(2)); // save the file name portion of the URL to our list
					}
					start = end;
				}
				// we've found all the srcset attributes on the current line
			}
			// we're at the end of the file now so the search is complete
			
			Collections.sort(fileNames); // a medium fast ascending "asciibetical" sort of fileNames
			for (String name : fileNames)
				System.out.println(name);
		}
		catch (FileNotFoundException fnfe) {
			System.out.printf("Cannot access file %s (%s)\n", args[0], fnfe.getMessage());
		}
		catch (IOException ioe) {
			System.out.printf("Error reading file %s (%s)\n", args[0], ioe.getMessage());
		}
	}
	
}