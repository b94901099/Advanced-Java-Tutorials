package EXTRA;

import java.util.*;
import java.io.*;

public class IS {

    public static void main(String[] args) {
        ArrayList<String> country = new ArrayList<String>();
        ArrayList<String> abr = new ArrayList<String>();

        try {
            String file = "D:\\CMU\\ISProject\\country.txt";
            BufferedReader in = new BufferedReader(new FileReader(file));
            String line;
            while ((line = in.readLine()) != null) {
                if (line.contains(";")) {
                    abr = new ArrayList<String>(country);
                    country.clear();
                } else {
                    country.add(line);
                }
            }
            in.close();

            System.out.println(abr);
            System.out.println(country);

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < country.size(); i++) {
                String template = "\t\t\tcase \"" + country.get(i) + "\":\n" + "\t\t\treturn \"" + abr.get(i) + "\";\n"
                        + "\t\t\tbreak;\n";
                sb.append(template);
            }

            FileWriter out = new FileWriter("D:\\CMU\\ISProject\\countryAbr.txt");
            out.write(sb.toString());
            out.close();
        } catch (IOException ex) {
            System.out.println("File Read Error: " + ex.getMessage());
        }
    }
}
