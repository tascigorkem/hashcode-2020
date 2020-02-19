package qualification;

import qualification.bean.Input;
import qualification.bean.Output;

import java.io.File;
import java.util.List;

public class Hashcode2020Problem {

    public static void main(String[] args) throws Exception {
//        args = new String[]{"a_example", "b_lovely_landscapes", "c_memorable_moments", "d_pet_pictures", "e_shiny_selfies"};
        args = new String[]{"a_example"};

        for (String s : args) {
            process(s);
        }
    }

    static void process(String fileName) throws Exception {
        File inFile = new File("src/resources/in/" + fileName + ".txt");
        Input input = Translator.getInput(inFile);

        List<String> slidesLines = Solver.solve(input);

        Output output = new Output();
        output.setSlidesCount(slidesLines.size());
        output.setSlidesLines(slidesLines);
        File outFile = new File("src/resources/out/" + fileName.split("_")[0] + ".out");
        Translator.writeOutput(output, outFile);
    }
}
