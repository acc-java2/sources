package edu.acc.java2.nigeria;

public class ComplexNumber /*implements Comparable<ComplexNumber> */{
    private double realPart;
    private double imaginaryPart;
    
    public ComplexNumber() {
        realPart = 0.0;
        imaginaryPart = 0.0;
    }
    
    public ComplexNumber(double r, double i) {
        this.realPart = r;
        this.imaginaryPart = i;
    }
    
    public double getRealPart() { return realPart; }
    public double getImaginaryPart() { return imaginaryPart; }
    public ComplexNumber add(ComplexNumber other) {
        return new ComplexNumber(
            this.realPart + other.realPart,
            this.imaginaryPart + other.imaginaryPart
        );
    }
    public ComplexNumber subtract(ComplexNumber other) {
        return new ComplexNumber(
            this.realPart - other.realPart,
            this.imaginaryPart - other.imaginaryPart
        );
    }
    public ComplexNumber multiply(ComplexNumber other) {
        return new ComplexNumber(
            this.realPart * other.realPart,
            this.imaginaryPart * other.imaginaryPart
        );
    }
    public ComplexNumber divide(ComplexNumber other) {
        return new ComplexNumber(
            this.realPart / other.realPart,
            this.imaginaryPart / other.imaginaryPart
        );
    }
    
    @Override
    public String toString() {
        char sign = imaginaryPart < 0.0 ? '\0' : '+';
        return String.format("%f %c %fi", realPart, sign, imaginaryPart);
    }
    
    /*
    @Override
    public int compareTo(ComplexNumber other) {
        if (this.realPart < other.realPart) return -1;
        else if (this.realPart > other.realPart) return 1;
        else if (this.imaginaryPart < other.imaginaryPart) return -1;
        else if (this.imaginaryPart > other.imaginaryPart) return 1;
        else return 0;
    }
*/
}
