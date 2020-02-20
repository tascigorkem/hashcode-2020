package qualification.bean;

import java.util.List;

public class Library {

    private Integer id;
    private Integer bookCount;
    private List<Book> books;
    private Integer totalScore;
    private Integer shippedBookCountPerDay;
    private Double valuePerDay; // totalScore / shippedBookCountPerDay
    private Double finalScore; // valuePerDay - signUpProcessDay
    private Integer signUpProcessDay;
    private Integer maxScoredBook;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookCount() {
        return bookCount;
    }

    public void setBookCount(Integer bookCount) {
        this.bookCount = bookCount;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Integer getShippedBookCountPerDay() {
        return shippedBookCountPerDay;
    }

    public void setShippedBookCountPerDay(Integer shippedBookCountPerDay) {
        this.shippedBookCountPerDay = shippedBookCountPerDay;
    }

    public Integer getSignUpProcessDay() {
        return signUpProcessDay;
    }

    public void setSignUpProcessDay(Integer signUpProcessDay) {
        this.signUpProcessDay = signUpProcessDay;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Double getValuePerDay() {
        return valuePerDay;
    }

    public void setValuePerDay(Double valuePerDay) {
        this.valuePerDay = valuePerDay;
    }

    public Double getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(Double finalScore) {
        this.finalScore = finalScore;
    }

    public Integer getMaxScoredBook() {
        return maxScoredBook;
    }

    public void setMaxScoredBook(Integer maxScoredBook) {
        this.maxScoredBook = maxScoredBook;
    }
}
