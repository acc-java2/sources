package edu.acc.java2.dr;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
This file contains ten thousand records where each record is an int followed by a double followed by
a boolean.

Write a Java program to print the average of all the ints in the file, the sum of all the doubles,
and the count of trues and the count of falses that occur in the file.
Here's my output:

C:\Users\javauser\Desktop\day1\hw1\bin>java edu.acc.java2.hw1.Main ..\day1.dat
The average int was ####
The double total was ####
There were #### trues and #### falses.
Obviously, the #### represent my answers, which will necessarily be different....
*/
public class Main {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java edu.acc.java2.dr.Main {filename}");
            return;
        }
        
        final int RECORDS = 10_000;
        try {
            if (Files.size(Paths.get(args[0])) != RECORDS * 13) {
                System.out.println("File " + args[0] + " is not big enough!");
                return;
            }
        } catch (IOException ex) {
            System.out.printf("File %s is too short!\n", args[0]);
        }
        
        try (DataInputStream dis = new DataInputStream(
                new BufferedInputStream(new FileInputStream(args[0])))) {
            int intSum = 0;
            double doubleSum = 0.0;
            int trues = 0, falses = 0;
            for (int n = 0; n < RECORDS; n++) {
                int i = dis.readInt();
                double d = dis.readDouble();
                boolean b = dis.readBoolean();
                intSum += i;
                doubleSum += d;
                if (b) trues++;
                else falses++;
            }
            double ave = (double)intSum / RECORDS;
            displayReport(ave, doubleSum, trues, falses);
        }
        catch (FileNotFoundException fnfe) {
            System.out.printf("Can't open %s: %s\n", args[0], fnfe.getMessage());
        }
        catch (IOException ioe) {
            System.out.printf("Error while reading %s: %s\n", args[0], ioe.getMessage());
        }
    }

    private static void displayReport(double ave, double doubleSum, int trues, int falses) {
        System.out.printf("The average of integers was %f\n", ave);
        System.out.printf("The sum of the doubles was %f\n", doubleSum);
        System.out.printf("There were %d trues and %d falses\n", trues, falses);
    }
    
}
