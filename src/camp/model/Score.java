package camp.model;

public class Score {
    private String scoreId;
    private String studentId;
    private String subjectId;
    private int round;
    private int score;
    private String grade; // 등급 추가
    private String subjectType; // 과목 유형 추가

    public Score(String studentId, String subjectId, int round, int score, String subjectType) {
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.round = round;
        this.score = score;
        this.subjectType = subjectType;
        this.grade = calculateGrade(score, subjectType);
    }

    private String calculateGrade(int score, String subjectType) {
        if (subjectType.equals("MANDATORY")) {
            if (score >= 95) return "A";
            if (score >= 90) return "B";
            if (score >= 80) return "C";
            if (score >= 70) return "D";
            if (score >= 60) return "F";
            return "N";
        } else { // 선택과목(CHOICE)
            if (score >= 90) return "A";
            if (score >= 80) return "B";
            if (score >= 70) return "C";
            if (score >= 60) return "D";
            if (score >= 50) return "F";
            return "N";
        }
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
        this.grade = calculateGrade(score, this.subjectType);
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }
}
