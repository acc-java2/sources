package edu.acc.java2.io;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

public class ReadBinary {
    
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println(
                    "Usage: java edu.acc.java2.io.ReadBinary {file name}");
            return; 
        }
        
        DataInputStream dis = new DataInputStream(
            new BufferedInputStream(new FileInputStream(args[0]))
        );
        
        byte b = dis.readByte();
        short s = dis.readShort();
        int i = dis.readInt();
        long el = dis.readLong();
        float f = dis.readFloat();
        double d = dis.readDouble();
        boolean n = dis.readBoolean();
        char c = dis.readChar();
        
        System.out.printf("%d %d %d %d %.0f %.0f, %s, %c\n", 
                b, s, i, el, f, d, n, c);
        
        dis.close();
    }
}
