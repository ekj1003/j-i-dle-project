package camp.model;

import camp.Util;

public class Score {
    private final String studentId;
    private final String subjectId;
    private final int round;
    private int score;
    private char grade; // 등급 추가
    private final String subjectType; // 과목 유형 추가

    public Score(String studentId, String subjectId, int round, int score, String subjectType) {
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.round = round;
        this.score = score;
        this.subjectType = subjectType;
        this.grade = Util.getGrade(score, subjectType);
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
    public void setScore(int newScore) {
        this.score = newScore;
        this.grade = Util.getGrade(score,this.subjectType);
    }

}
