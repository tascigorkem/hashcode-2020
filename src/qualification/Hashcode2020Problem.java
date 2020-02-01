package qualification;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Hashcode2020Problem {



    public static void main(String[] args) {

    }

    public Hashcode2020Problem() {


    }

    public List<String> solve() {
        return null;
    }

    protected static void save(String filename) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(filename));

            bw.flush();
        } catch (Exception ex) {
            System.err.println("Err"+ ex.getMessage());
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (Exception ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }

    protected static Hashcode2020Problem load(String filename) {
        BufferedReader br = null;

        int n = 0;
        List<String> photos = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(filename));
            int lineNo = 0;

            while (br.ready()) {
                // Get details
                if (lineNo == 0) {
                    String line = br.readLine();
                    n = Integer.parseInt(line);
                } else {
                    for (int j = 0; j < n; j++) {
                        String line = br.readLine();
                        String[] sp = line.split(" ");
                    }
                }
//
                lineNo++;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }

        return (new Hashcode2020Problem());
    }
}
