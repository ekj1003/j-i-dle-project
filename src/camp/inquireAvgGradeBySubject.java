package camp;

import camp.model.Student;
import camp.model.Subject;

import java.util.Objects;

public class inquireAvgGradeBySubject {

    // 수강생을 찾아서 과목별 평균 등급 조회 시작
    public static void mInquireAvgGradeBySubject() {
        Student who = util.findStudent(util.getStudentId());
        studentAvgGradeBySubject(who);
    }

    // 수강생 과목별 평균 등급 조회 코어 메서드
    public static void studentAvgGradeBySubject(Student student) {
        System.out.println(student.getStudentName() + "님의 과목별 평균 등급은 다음과 같습니다..");
        printAvgGradeBySubject(student, "MANDATORY");
        printAvgGradeBySubject(student, "CHOICE");
    }

    //수강생이 듣는 과목별로 평균등급을 출력해주는 메서드
    public static void printAvgGradeBySubject(Student student, String subjectTypeLabel) {
        for (Subject subject : util.listStudentSubjectByType(student, subjectTypeLabel)) {
            String stId = student.getStudentId();
            String sName = subject.getSubjectName();
            String suId = subject.getSubjectId();
            String simpleSubjectType;
            if (Objects.equals(subjectTypeLabel, "MANDATORY")) {
                simpleSubjectType = "(필)";
            } else {
                simpleSubjectType = "(선)";
            }
            char grade = util.avgGrade(stId, suId, subjectTypeLabel);
            System.out.printf("[ %s %s : %c ]\n", simpleSubjectType, sName, grade);
        }
    }
}
