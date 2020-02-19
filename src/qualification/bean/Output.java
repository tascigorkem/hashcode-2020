package qualification.bean;

import java.util.List;

public class Output {

    private Integer slidesCount;
    private List<String> slidesLines;

    public Integer getSlidesCount() {
        return slidesCount;
    }

    public void setSlidesCount(Integer slidesCount) {
        this.slidesCount = slidesCount;
    }

    public List<String> getSlidesLines() {
        return slidesLines;
    }

    public void setSlidesLines(List<String> slidesLines) {
        this.slidesLines = slidesLines;
    }
}
