package EXTRA;

import java.util.*;
import java.io.*;

public class IS {

    public static void main(String[] args) {
        String s = "Austria\n" +
"Belgium\n" +
"Bulgaria\n" +
"Croatia\n" +
"Cyprus\n" +
"Czech Republic\n" +
"Denmark\n" +
"Estonia\n" +
"Finland\n" +
"France\n" +
"Germany\n" +
"Greece\n" +
"Hungary\n" +
"Ireland\n" +
"Italy\n" +
"Latvia\n" +
"Lithuania\n" +
"Luxembourg\n" +
"Malta\n" +
"Netherlands\n" +
"Poland\n" +
"Portugal\n" +
"Romania\n" +
"Slovakia\n" +
"Slovenia\n" +
"Spain\n" +
"Sweden\n" +
"United Kingdom";
        String[] strs = s.split("\n");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            sb.append("\"" + strs[i] + "\", ");
        }
        System.out.println(sb.toString());
        
//        try {
//            String file = "D:\\CMU\\ISProject\\countrylist.txt";
//            BufferedReader in = new BufferedReader(new FileReader(file));
//            String line;
//            StringBuilder result = new StringBuilder();
//            
//            while ((line = in.readLine()) != null) {
//                String[] strs = line.split("\">");
//                result.append("map.put(\"" + strs[0].trim() + "\", \"0\");\n");
//            }
//            in.close();
//
//            System.out.println(result.toString());

//            FileWriter out1 = new FileWriter("D:\\CMU\\ISProject\\initiateWorldMap.txt");
//            out1.write(result.toString());
//            out1.close();
            
//        } catch (IOException ex) {
//            System.out.println("File Read Error: " + ex.getMessage());
//        }
    }
}
