package edu.acc.java2.divs;

import java.io.*;

public class DivCounter {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java DivCounter {html file}");
			return;
		}
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			int divCount = 0;
			String line;
			while ((line = br.readLine()) != null) {
				int start = 0;
				while (true) {
					start = line.indexOf("<div ", start);
					if (start == -1 ) break;
					else {
						divCount++;
						start += 5;
					}
				}
			}
			System.out.printf("There are %d <div> tags in %s\n", divCount, args[0]);
		}
		catch (IOException ioe) {}
	}
}