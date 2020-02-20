package qualification.bean;

public class Book {

    private Integer id;
    private Boolean scanned;
    private Integer score;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getScanned() {
        return scanned;
    }

    public void setScanned(Boolean scanned) {
        this.scanned = scanned;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
