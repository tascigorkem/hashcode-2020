package qualification;

import qualification.bean.Book;
import qualification.bean.Input;
import qualification.bean.Library;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Solver {

    private static List<Library> libraries;
    private static List<Library> sortedLibraries;

    private static Integer remainDay;
    private static final Integer BUFFER = 50;

    private Solver() {
    }

    static List<Library> solve(Input input) {
        libraries = new ArrayList<>();
        remainDay = input.getDayCount();

        calculateScores(input.getLibraries());

        Integer libraryCount = 0;
        for (Library library : sortedLibraries) {

            if (library.getSignUpProcessDay() < remainDay) {
                remainDay -= library.getSignUpProcessDay();
                processLibrary(library);
            }

            List<Book> scannedBooks = library.getBooks().stream().filter(book -> book.getScanned().equals(Boolean.TRUE)).collect(Collectors.toList());
            if (!scannedBooks.isEmpty()) {
                libraries.add(library);
                libraryCount++;
                if (libraryCount % BUFFER == 0) {
                    calculateScores(sortedLibraries);
                }
            }
        }
        return libraries;
    }

    private static void processLibrary(Library library) {
        List<Book> sortedBooks = library.getBooks().stream()
                .sorted(Comparator.comparing(Book::getScore).reversed())
                .collect(Collectors.toList());
        library.setBooks(sortedBooks);

        Integer maxScanBook = remainDay * library.getShippedBookCountPerDay();
        Integer scannedBookCount = 0;

        for (Book book : sortedBooks) {
            if (scannedBookCount <= maxScanBook) {
                book.setScanned(true);
            }
            scannedBookCount++;
        }
    }

    private static void calculateScores(List<Library> libraries) {
        libraries.forEach(library -> {
            List<Book> unscannedBooks = library.getBooks()
                    .stream().filter(book -> book.getScanned().equals(Boolean.FALSE))
                    .collect(Collectors.toList());

            Integer totalScore = unscannedBooks.stream().map(Book::getScore).reduce(0, Integer::sum);

            library.setTotalScore(totalScore);
            library.setValuePerDay(Double.valueOf(library.getTotalScore()) / library.getShippedBookCountPerDay());
            library.setFinalScore(library.getValuePerDay() - library.getSignUpProcessDay());

            Optional<Book> book = unscannedBooks.stream().max(Comparator.comparing(Book::getScore));

            if (book.isPresent()) {
                library.setMaxScoredBook(book.get().getScore());
            } else {
                library.setMaxScoredBook(0);
            }

        });

        sortedLibraries = libraries.stream()
                .sorted(Comparator.comparing(Library::getFinalScore).reversed()
                        .thenComparing(Library::getShippedBookCountPerDay)
                        .thenComparing(Library::getMaxScoredBook))
                .collect(Collectors.toList());
    }
}
