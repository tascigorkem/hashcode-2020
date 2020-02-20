package qualification;

import qualification.bean.Book;
import qualification.bean.Input;
import qualification.bean.Library;

import java.util.ArrayList;
import java.util.List;

public class Solver {

    static List<Book> books;

    public static List<Library> solve(Input input) {
        books = input.getBooks();
        return new ArrayList<>();
    }

    public static Integer compare() {
        return 0;
    }
}
