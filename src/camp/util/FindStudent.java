package camp.util;

import camp.CampManagementApplication;
import camp.model.Student;

public class FindStudent {
    public static Student findStudent(String studentId) {
        return CampManagementApplication.studentStore.stream()
                .filter(student -> student.getStudentId().equals(studentId))
                .findFirst()
                .orElse(null);
    }
}
