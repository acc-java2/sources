package edu.acc.java2.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

public class WriteText {
    
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: java edu.acc.java2.io.WriteText {filename}");
            return;
        }
        
        FileWriter fw = new FileWriter(args[0]);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);
        
        String output = "She sells seashells by the seasure.\n" +
                "He heaves the same heavy heels he hove before.\n";
        pw.printf("The output is:\n\"%s\"", output);
        
        pw.close();
        bw.close();
        fw.close();
    }
    
}
