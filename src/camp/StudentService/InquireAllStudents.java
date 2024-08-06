package camp.StudentService;

import camp.CampManagementApplication;
import camp.model.Student;
import camp.model.Subject;

import static camp.util.ListSubjectByType.listStudentSubjectByType;

public class InquireAllStudents {
    //수강생 전체 목록 조회
    public static void inquireAllStudent() {
        System.out.println("\n수강생 목록을 조회합니다...");
        for (Student student : CampManagementApplication.studentStore) {
            //ID
            System.out.printf("%s ",student.getStudentId());
            //이름
            System.out.printf("%s ",student.getStudentName());
            //상태
            System.out.print("[상태:" + student.getStatus() + "] ");
            //수강 과목
            System.out.print("[수강 과목:(필):");
            for (Subject subject : listStudentSubjectByType(student, "MANDATORY")) {
                System.out.printf("%s,",subject.getSubjectName());
            }
            System.out.print("(선):");
            for (Subject subject : listStudentSubjectByType(student, "CHOICE")) {
                System.out.printf("%s,",subject.getSubjectName());
            }
            System.out.print("] \n");
        }
        System.out.println("\n수강생 목록 조회 성공!");
    }
}
