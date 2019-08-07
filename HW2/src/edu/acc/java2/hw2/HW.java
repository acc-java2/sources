package edu.acc.java2.hw2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class HW {
    
    public static void main(String[] args) {
        File file = chooseFile();
        try {
            int population = getPopulationFrom(file);
            showResult(population, file.getAbsolutePath());
        }
        catch (FileNotFoundException fnfe) {
            errorDialog("Cannot access", file.getAbsolutePath(), fnfe.getMessage());
        }
        catch (IOException ioe) {
            errorDialog("Error reading", file.getAbsolutePath(), ioe.getMessage());
        }
        catch (NullPointerException npe) {
            errorDialog("No valid file chosen", "", "CSV");
        }
        System.exit(0);
    }
    
    private static File chooseFile() {
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new FileNameExtensionFilter("CSV", "csv"));
        fc.setDialogTitle("Select the Census file");
        int result = fc.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            String ext = fc.getSelectedFile().getName().toLowerCase();
            if (ext.endsWith("csv"))
                return fc.getSelectedFile();
        }
        return null;
    }
    
    public static int getPopulationFrom(File file) throws FileNotFoundException, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            
            br.readLine();
            
            int population = 0;
            String line;
            
            while ((line = br.readLine()) != null) {
                String[] cols = line.split(",");
                if (cols.length > 7)
                    population += Integer.parseInt(cols[7]);
            }
            population /= 2;
            return population;
        }
    }
    
    private static void showResult(int population, String path) {
        String popText = NumberFormat.getInstance().format(population);
        String output = String.format("The US Population in 2010 was %s", popText);
        JOptionPane.showMessageDialog(null, output, "From " + path, JOptionPane.INFORMATION_MESSAGE);
    }
    
    private static void errorDialog(String msg, String rsrc, String except) {
        String result = String.format("%s %s (%s)\n", msg, rsrc, except);
        JOptionPane.showMessageDialog(null, result, "Warning", JOptionPane.ERROR_MESSAGE);
    }


}
