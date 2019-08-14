package edu.acc.java2.nigeria;

import java.util.Iterator;

public class IterableString implements Iterable<Character>, Iterator<Character> {
    private String theString;
    
    public IterableString() {
        theString = "";
    }
    
    public IterableString(String other) {
        theString = other;
    }
    
    public String get() {
        return theString;
    }
    
    @Override
    public Iterator<Character> iterator() {
        return this;
    }
    
    private int cursor = 0;

    @Override
    public boolean hasNext() {
        return theString.length() > cursor;
    }

    @Override
    public Character next() {
        char c = theString.charAt(cursor);
        cursor++;
        return c;
    }
    
    public void reset() {
        cursor = 0;
    }
    
}
