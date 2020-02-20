package qualification;

import qualification.bean.Book;
import qualification.bean.Input;
import qualification.bean.Library;
import qualification.bean.Output;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Translate files into beans and reverse.
 */
public class Translator {

    private Translator() {
    }

    // Obtain Input Bean from Input File
    public static Input getInput(File file) throws IOException {
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
                        if (Integer.parseInt(score) != 0) {
                            books.add(book);
                            bookCount++;
                        }

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

                    List<Integer> bookIdsInteger = bookIds.stream()
                            .map(Integer::parseInt)
                            .collect(Collectors.toList());

                    List<Book> retainBooks = books.stream()
                            .filter(book -> bookIdsInteger.contains(book.getId()))
                            .sorted(Comparator.comparing(Book::getScore).reversed())
                            .collect(Collectors.toList());

                    library.setBooks(retainBooks);

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
    public static void writeOutput(Output output, File outFile) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)))) {
            writer.write("" + output.getLibraryCount());
            writer.newLine();
            if (output.getLibraries() != null) {
                for (Library library : output.getLibraries()) {
                    List<Book> scannedBooks = library.getBooks().stream().filter(book -> book.getScanned().equals(Boolean.TRUE)).collect(Collectors.toList());
                    if (!scannedBooks.isEmpty()) {

                        writer.write(library.getId() + " " + scannedBooks.size());
                        writer.newLine();

                        StringBuilder scannedBookLine = new StringBuilder();
                        for (Book scannedBook :
                                scannedBooks) {
                            scannedBookLine.append(" ").append(scannedBook.getId());
                        }
                        writer.write("" + scannedBookLine.toString().trim() + "");
                        writer.newLine();
                    }
                }
                writer.write(" ");
            }
        }
    }

}