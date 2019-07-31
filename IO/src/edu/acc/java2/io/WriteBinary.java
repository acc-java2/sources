package edu.acc.java2.io;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/*
Create a binary file and write a mix of Java primitives
*/

public class WriteBinary {

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println(
                "Usage: java edu.acc.java2.io.WriteBinary {filename}");
            return;
        }
        FileOutputStream fos = new FileOutputStream(args[0]);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        DataOutputStream dos = new DataOutputStream(bos);
        
        dos.writeByte(3);
        dos.writeShort(3);
        dos.writeInt(3);
        dos.writeLong(3L);
        dos.writeFloat(3.0F);
        dos.writeDouble(3.0);
        dos.writeBoolean(true);
        dos.writeChar('3');
        
        dos.close();
        bos.close();
        fos.close();
    }
    
}
