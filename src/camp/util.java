package camp;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class util {
    public static Scanner sc = new Scanner(System.in);

    //수강생이 듣는 과목을 (전공,선택)에 따라 리스트로 반환
    public static List<Subject> listStudentSubjectByType(Student student, String subjectType) {
        List<Subject> subjectList = new ArrayList<>();

        for (String subjectId : student.getSubjectList()) {
            subjectList.add(findSubjectById(subjectId));
        }

        return subjectList.stream()
                .filter(s -> subjectType.equals(s.getSubjectType()))
                .toList();
    }

    //점수를 (전공,선택)에 따라 등급으로 변환해주는 메서드
    public static char getGrade(double result, String subjectTypeLabel) {
        char Grade = 'N';
        if (Objects.equals(subjectTypeLabel, "MANDATORY")) {
            if (result >= 95) {
                Grade = 'A';
            } else if (result >= 90) {
                Grade = 'B';
            } else if (result >= 80) {
                Grade = 'C';
            } else if (result >= 70) {
                Grade = 'D';
            } else if (result >= 60) {
                Grade = 'F';
            }
        } else if (Objects.equals(subjectTypeLabel, "CHOICE")) {
            if (result >= 90) {
                Grade = 'A';
            } else if (result >= 80) {
                Grade = 'B';
            } else if (result >= 70) {
                Grade = 'C';
            } else if (result >= 60) {
                Grade = 'D';
            } else if (result >= 50) {
                Grade = 'F';
            }
        }
        return Grade;

    }

    // 수강생의 ID 찾기
    public static String getStudentId() {
        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        return sc.next();
    }

    // 수강생의 ID로 학생 객체 리턴
    public static Student findStudent(String studentId) {
        return CampManagementApplication.studentStore.stream()
                .filter(student -> student.getStudentId().equals(studentId))
                .findFirst()
                .orElse(null);
    }

    //수강생이 듣는 과목 ID로 과목 객체 리턴
    public static Subject findSubjectById(String subjectId) {
        return CampManagementApplication.subjectStore.stream()
                .filter(subject -> subject.getSubjectId().equals(subjectId))
                .findFirst()
                .orElse(null);
    }

    //수강생의 해당 과목 평균 등급을 반환
    public static char avgGrade(String studentId, String subjectId, String subjectTypeLabel) {
        List<Score> filteredScore = Store.scoreStore.stream()
                .filter(score -> studentId.equals(score.getStudentId()) && subjectId.equals(score.getSubjectId()))
                .toList();
        double result = 0;
        double average = 0;

        for (Score score : filteredScore) {
            result += score.getScore();
        }
        average = result / filteredScore.size();
        return util.getGrade(average, subjectTypeLabel);
    }
}