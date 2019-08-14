package edu.acc.java2.nigeria;

import java.util.Comparator;

public class ComplexNumberComparator implements Comparator<ComplexNumber>{

    @Override
    public int compare(ComplexNumber a, ComplexNumber b) {
        if (a.getRealPart() < b.getRealPart()) return -1;
        else if (a.getRealPart() > b.getRealPart()) return 1;
        else if (a.getImaginaryPart() < b.getImaginaryPart()) return -1;
        else if (a.getImaginaryPart() > b.getImaginaryPart()) return 1;
        else return 0;
    }
      
}
