package qualification;

import qualification.bean.Input;
import qualification.bean.Library;
import qualification.bean.Output;

import java.io.File;
import java.util.List;

public class Hashcode2020Problem {

    public static void main(String[] args) throws Exception {
//        args = new String[]{"a_example", "b_read_on", "c_incunabula", "d_tough_choices", "e_so_many_books", "f_libraries_of_the_world"};
//        args = new String[]{"a_example", "b_read_on", "c_incunabula"};

        for (String s : args) {
            process(s);
        }
    }

    static void process(String fileName) throws Exception {

        long start = System.currentTimeMillis();

        File inFile = new File("src/resources/in/" + fileName + ".txt");
        Input input = Translator.getInput(inFile);

        List<Library> libraries = Solver.solve(input);

        Output output = new Output();
        output.setLibraryCount(libraries.size());
        output.setLibraries(libraries);

        String prefixFileName = fileName.split("_")[0];
        File outFile = new File("src/resources/out/" + prefixFileName + ".out");
        Translator.writeOutput(output, outFile);

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println(prefixFileName + " processed. Time: " + timeElapsed + " ms.");
    }
}
