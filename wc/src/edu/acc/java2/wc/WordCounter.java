package edu.acc.java2.wc;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class WordCounter {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java WC {text-file}");
			return;
		}
		Map<String, Integer> wordCounts = new TreeMap<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			Pattern wordPatt = Pattern.compile("\\b[A-Za-z]+\\b");
			String line;
			while ((line = br.readLine()) != null) {
				Matcher m = wordPatt.matcher(line.toLowerCase());
				while (m.find()) {
					if (!wordCounts.containsKey(m.group()))
						wordCounts.put(m.group(), new Integer(0));
					int count = wordCounts.get(m.group());
					count++;
					wordCounts.put(m.group(), count);
				}
			}
			for (String word : wordCounts.keySet())
				System.out.printf("(%6d) %s\n", wordCounts.get(word), word);
			
			// sort by value descending - requires the entries stored in the map
			List<Map.Entry<String,Integer>> entries = new LinkedList<>(wordCounts.entrySet());
			
			// first - we can use a named instance (wcc) of a named comparator class (WordCountComparator, below)
			WordCountComparator wcc = new WordCountComparator();
			Collections.sort(entries, wcc);
			
			// second - we can use an anonymous instance of an anonymous inner class
			Collections.sort(entries, new Comparator<Map.Entry<String,Integer>>() {
				public int compare(Map.Entry<String,Integer> o1, Map.Entry<String,Integer> o2) {
					return o2.getValue().compareTo(o1.getValue());
				}
			});
			
			// third - we can use a lambda expression
			Collections.sort(entries, (a,b) -> b.getValue().compareTo(a.getValue()));
			
			// now we can build a new map with the map entries we sorted earlier
			HashMap<String, Integer> sorted = new LinkedHashMap<>();
			for (Map.Entry<String, Integer> entry : entries)
				sorted.put(entry.getKey(), entry.getValue());
			for (String word : sorted.keySet())
				System.out.printf("(%6d) %s\n", sorted.get(word), word);
		}
		catch (FileNotFoundException fnfe) {
			System.out.printf("Cannot open %s (%s)\n", args[0], fnfe.getMessage());
		}
		catch (IOException ioe) {
			System.out.printf("Error reading %s (%s)\n", args[0], ioe.getMessage());
		}
	}
}

class WordCountComparator implements Comparator<Map.Entry<String,Integer>> {
	@Override
	public int compare(Map.Entry<String,Integer> o1, Map.Entry<String,Integer> o2) {
		return o2.getValue().compareTo(o1.getValue());
	}
}