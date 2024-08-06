package camp.model;

public class Score {
    private String scoreId;
    private String studentId;
    private String subjectId;
    private int round;
    private int score;
    private String grade; // 등급 추가

    public Score(String studentId, String subjectId, int round, int score) {
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.round = round;
        this.score = score;
        this.grade = calculateGrade(score);
    }

    private String calculateGrade(int score) {
        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        if (score >= 60) return "D";
        return "F";
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

    public String getGrade() {
        return grade;
    }

    // Setter
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public void setRound(int round) {
        this.round = round;
    }

    // Score를 수정하는 함수
    public void setScore(int newScore) {
        this.score = newScore;
        this.grade = calculateGrade(score);
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}