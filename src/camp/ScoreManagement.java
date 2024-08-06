package camp;

import camp.model.Student;
import camp.model.Subject;

import java.util.Objects;

public class ScoreManagement {















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
