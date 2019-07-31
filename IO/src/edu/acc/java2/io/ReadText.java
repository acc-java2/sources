package edu.acc.java2.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadText {

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Foo!");
            return;
        }
        // Try-with-Resources
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            int lineno = 0;
            String line;
            while ((line = br.readLine()) != null) {
                lineno++;
                System.out.printf("%02d) %s\n", lineno, line);
            }
        }
        catch (FileNotFoundException fnfe) {
            System.out.println("Cannot open " + args[0] + ": " + fnfe.getMessage());
        }
        catch (IOException ioe) {
            System.out.println("Error while reading " + args[0] + ": " + ioe.getMessage());
        }

    }

}
