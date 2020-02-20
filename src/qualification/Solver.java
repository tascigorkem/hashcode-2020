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

    private static final Integer BUFFER = 50;
    private static List<Library> libraries;
    private static List<Library> sortedLibraries;
    private static Integer remainDay;

    private Solver() {
    }

    static List<Library> solve(Input input) {
        libraries = new ArrayList<>();
        remainDay = input.getDayCount();

        sortedLibraries = input.getLibraries();
        calculateScores();
        sortedLibraries();

        Integer scannedLibraryCount = 0;
        for (Library library : sortedLibraries) {

            if (library.getSignUpProcessDay() < remainDay) {
                remainDay -= library.getSignUpProcessDay();

                if (!scanBooks(library).isEmpty()) {
                    libraries.add(library);
                    scannedLibraryCount++;

                    if (scannedLibraryCount % BUFFER == 0) {
                        calculateScores();
                        sortedLibraries();
                    }
                }
            }
        }
        return libraries;
    }

    private static List<Book> scanBooks(Library library) {

        List<Book> scannedBooks = new ArrayList<>();
        Integer maxScanBook = remainDay * library.getShippedBookCountPerDay();

        Integer scannedBookCount = 0;
        for (Book book : library.getBooks()) {
            if (scannedBookCount <= maxScanBook) {
                book.setScanned(true);
                scannedBookCount++;
                scannedBooks.add(book);
            }
        }
        return scannedBooks;
    }

    private static void calculateScores() {
        sortedLibraries.forEach(library -> {
            List<Book> unScannedBooks = library.getBooks()
                    .stream().filter(book -> book.getScanned().equals(Boolean.FALSE))
                    .collect(Collectors.toList());

            Integer totalScore = unScannedBooks.stream().map(Book::getScore).reduce(0, Integer::sum);

            library.setTotalScore(totalScore);
            library.setValuePerDay(Double.valueOf(library.getTotalScore()) / library.getShippedBookCountPerDay());
            library.setFinalScore(library.getValuePerDay() - library.getSignUpProcessDay());

            Optional<Book> maxScoredUnScannedBook = unScannedBooks.stream().max(Comparator.comparing(Book::getScore));

            if (maxScoredUnScannedBook.isPresent()) {
                library.setMaxScoredBook(maxScoredUnScannedBook.get().getScore());
            } else {
                library.setMaxScoredBook(0);
            }

        });
    }

    private static void sortedLibraries() {
        sortedLibraries = sortedLibraries.stream()
                .sorted(Comparator.comparing(Library::getFinalScore).reversed()
                        .thenComparing(Library::getShippedBookCountPerDay).reversed()
                        .thenComparing(Library::getMaxScoredBook).reversed())
                .collect(Collectors.toList());
    }
}
