package camp.model;

import java.util.List;

public class Student {
    private String studentId;
    private String studentName;
    private List<String> subjectList;
    private String status;

    public Student(String seq, String studentName) {
        this.studentId = seq;
        this.studentName = studentName;
    }

    // Getter
    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

}
