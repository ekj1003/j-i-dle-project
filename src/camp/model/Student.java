package camp.model;

import java.util.List;

public class Student {
    private final String studentId; // 수강생 ID
    private final String studentName; // 수강생 이름
    private List<String> subjectList;
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

    public List<String> getSubjectList() {
        return subjectList;
    }

    public String getStatus() {
        return status;
    }


    // Setter
    public void setStudentStatus(String status) { this.status = status; }

    public void setSubjectList(List<String> subjectIds){ this.subjectList = subjectIds;}
}
