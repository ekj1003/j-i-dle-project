package camp.model;

public class Score {
    private String scoreId;
    private String studentId;
    private String subjectId;
    private int round;
    private int score;
    private char grade;

    public Score(String studentId, String subjectId, int round, int score) {
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.round = round;
        this.score = score;
    }

    // Getter

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

    public char getGrade() {
        return grade;
    }

    // Setter
    // Score를 수정하는 함수
    public void setScore(int newScore) {
        this.score = newScore;
    }
}