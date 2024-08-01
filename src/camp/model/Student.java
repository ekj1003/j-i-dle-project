package camp.model;

import java.util.List;

public class Student {
    private String studentId; // 수강생 ID
    private String studentName; // 수강생 이름
    private List<String> subjectList; // 수강 과목ID 목록
    private String status = "Green"; // 상태


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
    public String getStudentStatus() {
        return status;
    }

    // Setter
    public void setStatus(String status) {
        this.status = status;
    }



}
