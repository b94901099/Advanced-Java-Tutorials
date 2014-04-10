package CodeChallange;

import java.io.*;

public class readInt {

    public static void sumFile(String name) {
        try {
            int total = 0;
            BufferedReader in = new BufferedReader(new FileReader(name));

            String s;
            while ((s = in.readLine()) != null) {
                total += Integer.parseInt(s);
            }
            System.out.println(total);
            in.close();
        } catch (Exception xc) {
            xc.printStackTrace();
        }
    }

    public static void main(String[] args) {
    }
}
