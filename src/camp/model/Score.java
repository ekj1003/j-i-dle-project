package camp.model;

public class Score {
    private String scoreId;
    private String studentId;
    private String subjectId;
    private int round;
    private int score;
    private String grade;

    // 필드 초기화 생성자
    public Score(String scoreId, String studentId, String subjectId, int round, int score) {
        this.scoreId = scoreId;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.round = round;
        this.score = score;
        this.grade = grade;
    }

    // Getter, Setter
    public String getScoreId() {
        return scoreId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public int getRound() {
        return round;
    }

    public int getScore() {
        return score;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}


