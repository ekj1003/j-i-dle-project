package camp.util;

import camp.CampManagementApplication;
import camp.model.Student;

public class FindStudent {
    //학생 ID로 학생 객체 리턴
    public static Student findStudent(String studentId) {
        return CampManagementApplication.studentStore.stream()
                .filter(student -> student.getStudentId().equals(studentId))
                .findFirst()
                .orElse(null);
    }
}
