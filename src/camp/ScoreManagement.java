package camp;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;
import camp.CampManagementApplication;
import camp.Util;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ScoreManagement {

    private static Scanner sc = new Scanner(System.in);

    // 수강생의 과목별 회차 점수 수정
    public static void updateRoundScoreBySubject() {
        String studentId = Util.getStudentId(); // 관리할 수강생 고유 번호
        // 기능 구현 (수정할 과목 및 회차, 점수)
        System.out.print("수정할 과목 입력: ");
        String subjectName = sc.next();
        System.out.print("수정할 회차 입력: ");
        int round = sc.nextInt();
        System.out.println("수정할 점수 입력: ");
        int newScore = sc.nextInt();

        String subjectId="";

        for(Subject subject : CampManagementApplication.subjectStore) {
            if(subject.getSubjectName().equals(subjectName)) subjectId = subject.getSubjectId();
        }

        System.out.println("시험 점수를 수정합니다...");
        // 기능 구현
        for(Score score : CampManagementApplication.scoreStore) {
            if(score.getStudentId().equals(studentId) && score.getRound() == round && score.getSubjectId().equals(subjectId)) {
                score.setScore(newScore);
            }
        }
        System.out.println("\n점수 수정 성공!");
    }

    // 수강생의 과목별 평균 등급 조회
    public static void inquireAVGGradeBySubject() {
        String input = Util.getStudentId();
        Student student = Util.findStudent(input);
        System.out.println( student.getStudentName()+"님의 과목별 평균 등급은 다음과 같습니다..");
        printAVGGradebySubject(student,"MANDATORY");
        printAVGGradebySubject(student,"CHOICE");
    }
    //수강생이 듣는 과목별로 평균등급을 출력해주는 메서드
    public static void printAVGGradebySubject (Student student, String subjectTypeLabel) {
        for (Subject subject : Util.listStudentSubjectByType(student, subjectTypeLabel)) {
            String stid = student.getStudentId();
            String sName = subject.getSubjectName();
            String sId = subject.getSubjectId();
            String simpleSubjectType;
            if (Objects.equals(subjectTypeLabel, "MANDATORY")) {simpleSubjectType = "(필)";} else {simpleSubjectType = "(선)";}
            char grade = Util.getAvgGradeBySubject(stid, sId, subjectTypeLabel);
            System.out.printf("[ %s %s : %c ]\n",simpleSubjectType,sName,grade);
        }
    }
}
