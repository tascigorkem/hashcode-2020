package qualification;

import qualification.bean.Input;
import qualification.bean.Output;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * Translate files into beans and reverse.
 */
public class Translator {

    // Obtain Input Bean from Input File
    public static Input getInput(File file) throws Exception {
        Input input = new Input();
        boolean firstLine = true;
//		List<Photo> photos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int lineCount = 0;
            for (String line; (line = br.readLine()) != null; ) {
                List<String> numbers = Arrays.asList(line.split(" "));
                if (firstLine) {
//					input.setPhotosCount(Integer.parseInt(numbers.get(0)));
                    //code
                    firstLine = false;
                    lineCount = 0;
                } else {
//					Photo photo = new Photo();
//					photos.add(photo);
                    //code
                    lineCount++;
                }
            }
        }
        return input;
    }

    // Write Output File from Output Bean
    public static void writeOutput(Output output, File outFile) throws Exception {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)))) {
            writer.write("" + output.getSlidesCount() + "\n");
            if (output.getSlidesLines() != null) {
                for (String line : output.getSlidesLines()) {
                    String lineString = line;
                    writer.write("" +
                            lineString.substring(1, lineString.length() - 1) +
                            "\n");
                }
            }
        }
    }

}