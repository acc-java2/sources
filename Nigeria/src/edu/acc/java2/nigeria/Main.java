package edu.acc.java2.nigeria;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static final String EMAIL = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Requires a text file argument");
            return;
        }
        try ( BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            final Pattern emailPattern = Pattern.compile(EMAIL);
            Set<String> emailSet = new HashSet<>();
            String line;
            while ((line = br.readLine()) != null) {
                Matcher m = emailPattern.matcher(line);
                while (m.find()) {
                    emailSet.add(m.group());
                }
            }
            Map<String, Integer> emailMap = new HashMap<>();
            List<String> eduList = new ArrayList<>();

            /*
            for (String email : emailSet) { // Iterator<String>
                int length = email.substring(0, email.indexOf("@")).length();
                emailMap.put(email, length);
                if (email.endsWith(".edu") || email.endsWith(".EDU"))
                    eduList.add(email);
            }
             */
            // This is what the enhanced for loop is DOING
            Iterator<String> it = emailSet.iterator();
            while (it.hasNext()) {
                String email = it.next();
                int length = email.substring(0, email.indexOf("@")).length();
                emailMap.put(email, length);
                if (email.endsWith(".edu") || email.endsWith(".EDU")) {
                    eduList.add(email);
                }
            }

            for (int i = 0; i < 10; i++) {
                String email = eduList.get(i);
                System.out.printf("%s (%d)\n", email, emailMap.get(email));
            }

            IterableString is = new IterableString("She sells seashells by the seashore");
            for (char c : is) {
                System.out.println(c);
            }
            is.reset();
            for (char c : is) {
                System.out.println(c);
            }

            ComplexNumber i = new ComplexNumber(0, 1);
            ComplexNumber cn1 = new ComplexNumber(4.2, -18.7);
            ComplexNumber cn2 = new ComplexNumber(-18.7, 4.2);
            ComplexNumber cn3 = new ComplexNumber(0, 1);
            List<ComplexNumber> cns = Arrays.asList(
                    i, cn1, cn2, cn3
            );
            CNComparator bob = new CNComparator();
            Collections.sort(cns, bob);
            Collections.reverse(cns);
            System.out.println(cns);

            Collections.sort(cns, new Comparator<ComplexNumber>() {
                @Override
                public int compare(ComplexNumber a, ComplexNumber b) {
                    if (a.getRealPart() < b.getRealPart()) {
                        return -1;
                    } else if (a.getRealPart() > b.getRealPart()) {
                        return 1;
                    } else if (a.getImaginaryPart() < b.getImaginaryPart()) {
                        return -1;
                    } else if (a.getImaginaryPart() > b.getImaginaryPart()) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            });
            
            Collections.sort(cns, (a, b) -> {
                if (a.getRealPart() < b.getRealPart()) return -1;
                else if (a.getRealPart() > b.getRealPart()) return 1;
                else if (a.getImaginaryPart() < b.getImaginaryPart()) return -1;
                else if (a.getImaginaryPart() > b.getImaginaryPart()) return 1;
                else return 0;
            });

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Can''t open {0}", args[0]);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Error reading " + args[0]);
        }
    }

}

class CNComparator implements Comparator<ComplexNumber> {

    @Override
    public int compare(ComplexNumber a, ComplexNumber b) {
        if (a.getRealPart() < b.getRealPart()) {
            return -1;
        } else if (a.getRealPart() > b.getRealPart()) {
            return 1;
        } else if (a.getImaginaryPart() < b.getImaginaryPart()) {
            return -1;
        } else if (a.getImaginaryPart() > b.getImaginaryPart()) {
            return 1;
        } else {
            return 0;
        }
    }

}
