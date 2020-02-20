package qualification;

import qualification.bean.Book;
import qualification.bean.Input;
import qualification.bean.Library;
import qualification.bean.Output;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Translate files into beans and reverse.
 */
public class Translator {

    // Obtain Input Bean from Input File
    public static Input getInput(File file) throws Exception {
        Input input = new Input();
        boolean firstLine = true;
        boolean secondLine = true;
        List<Book> books = new ArrayList<>();
        List<Library> libraries = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int libraryCount = 0;
            int bookCount = 0;
            for (String line; (line = br.readLine()) != null; ) {
                List<String> numbers = Arrays.asList(line.split(" "));
                if (firstLine) {
                    input.setBookCount(Integer.parseInt(numbers.get(0)));
                    input.setLibraryCount(Integer.parseInt(numbers.get(1)));
                    input.setDayCount(Integer.parseInt(numbers.get(2)));
                    //code
                    firstLine = false;
                } else if (secondLine) {
                    for (String score :
                            numbers) {

                        Book book = new Book();
                        book.setId(bookCount);
                        book.setName("book " + bookCount);
                        book.setScore(Integer.parseInt(score));
                        book.setScanned(false);
                        books.add(book);
                        bookCount++;

                    }
                    secondLine = false;
                } else {
                    Library library = new Library();
                    library.setId(libraryCount);
                    library.setBookCount(Integer.parseInt(numbers.get(0)));
                    library.setSignUpProcessDay(Integer.parseInt(numbers.get(1)));
                    library.setShippedBookCountPerDay(Integer.parseInt(numbers.get(2)));

                    String secondLibraryLine = br.readLine();
                    List<String> bookIds = Arrays.asList(secondLibraryLine.split(" "));
                    List<Integer> bookIdsInteger = bookIds.stream().map(bookId -> Integer.parseInt(bookId)).collect(Collectors.toList());
                    List<Book> retainBooks = books.stream().filter(book -> bookIdsInteger.contains(book.getId())).collect(Collectors.toList());
                    library.setBooks(retainBooks);

                    Integer totalScore = 0;

                    List<Integer> scores = retainBooks.stream().map(Book::getScore).collect(Collectors.toList());
                    for (Integer score :
                            scores) {
                        totalScore += score;
                    }

                    library.setTotalScore(totalScore);

                    libraries.add(library);
                    libraryCount++;
                }
            }
        }
        input.setBooks(books);
        input.setLibraries(libraries);
        return input;
    }

    // Write Output File from Output Bean
    public static void writeOutput(Output output, File outFile) throws Exception {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)))) {
            writer.write("" + output.getLibraryCount() + "\n");
            if (output.getLibraries() != null) {
                for (Library library : output.getLibraries()) {

                    List<Book> scannedBooks = library.getBooks().stream().filter(book -> book.getScanned().equals(Boolean.TRUE)).collect(Collectors.toList());
                    writer.write("" +
                            library.getId() + " " + scannedBooks.size() +
                            "\n");

                    StringBuilder scannedBookLine = new StringBuilder();
                    for (Book scannedBook :
                            scannedBooks) {
                        scannedBookLine.append(" ").append(scannedBook.getId());
                    }
                    writer.write("" +
                            scannedBookLine +
                            "\n");
                }
            }
        }
    }

}