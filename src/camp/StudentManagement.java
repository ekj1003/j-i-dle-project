package camp;

import camp.model.Student;
import camp.model.Subject;

import java.util.List;

public class StudentManagement {



    // 수강생 목록 조회
    public static void inquireStudent() {
        System.out.println("\n수강생 목록을 조회합니다...");
        for (Student student : CampManagementApplication.getStudentStore()) {
            System.out.printf("%s ",student.getStudentId());
            System.out.printf("%s ",student.getStudentName());
            System.out.printf("[상태:%s ]",student.getStatus());
            System.out.print(" [수강 과목 (필):");
            for (Subject subject : Util.listStudentSubjectByType(student, "MANDATORY")) {
                System.out.printf("%s,",subject.getSubjectName());
            }
            System.out.print("(선):");
            for (Subject subject : Util.listStudentSubjectByType(student, "CHOICE")) {
                System.out.printf("%s,",subject.getSubjectName());
            }
            System.out.print("]\n");
        }
        System.out.println("\n수강생 목록 조회 성공!");
    }

    //상태별 수강생 목록 조회
    public static void inquireStudentByStatus() {
        System.out.println("\n 상태별 수강생 목록을 조회합니다...");
        filterAndPrintStudentsByStatus("Green");
        filterAndPrintStudentsByStatus("Yellow");
        filterAndPrintStudentsByStatus("Red");
        System.out.println("\n상태별 수강생 목록 조회 성공!");
    }
    //상태별로 필터링해주는 메서드
    public static void filterAndPrintStudentsByStatus (String status) {
        List<Student> filteredStudents = CampManagementApplication.getStudentStore().stream()
                .filter(student -> status.equals(student.getStatus()))
                .toList();

        System.out.println("\n상태 : " + status + " 인 학생들");
        for (Student student : filteredStudents) {
            System.out.println(student.getStudentId() + " " +  student.getStudentName());
        }
    }
}
