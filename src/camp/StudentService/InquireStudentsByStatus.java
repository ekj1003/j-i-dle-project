package camp.StudentService;

import camp.CampManagementApplication;
import camp.model.Student;

import java.util.List;

public class InquireStudentsByStatus {
    //상태별 수강생 목록 조회
    public static void inquireStudentByStatus() {
        System.out.println("\n 상태별 수강생 목록을 조회합니다...");
        filterAndPrintStudentsByStatus("Red");
        filterAndPrintStudentsByStatus("Green");
        filterAndPrintStudentsByStatus("Yellow");
        System.out.println("\n상태별 수강생 목록 조회 성공!");
    }

    //상태별로 필터링해주는 메서드
    public static void filterAndPrintStudentsByStatus (String status) {
        List<Student> filteredStudents = CampManagementApplication.studentStore.stream()
                .filter(student -> status.equals(student.getStatus()))
                .toList();

        System.out.println("\n상태 : " + status + " 인 학생들");
        for (Student student : filteredStudents) {
            System.out.println(student.getStudentId() + " " +  student.getStudentName());
        }
    }
}
