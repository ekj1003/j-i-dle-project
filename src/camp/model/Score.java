package camp.model;

public class Score {
    private String scoreId;
    private String studentId;
    private String subjectId;
    private int round;
    private int score;
    private char grade;

    public Score(String seq) {
        this.scoreId = seq;
    }

    // Getter
    public String getScoreId() {
        return scoreId;
    }

}
