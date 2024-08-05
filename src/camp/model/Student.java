package camp.model;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String studentId; // 수강생 ID
    private String studentName; // 수강생 이름
    private List<String> subjectList;
    private List<Subject> subjectListTypeSubject; // 수강 과목ID 목록
    private String status = "Green"; // 상태


    public Student(String seq, String studentName) {
        this.studentId = seq;
        this.studentName = studentName;
        this.subjectListTypeSubject = new ArrayList<>();
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

    public List<Subject> getSubjectListTypeSubject() {
        return subjectListTypeSubject;
    }

    public String getStatus() {
        return status;
    }



    // Setter
    // 수강생의 상태 수정 setter 메서드
    public void setStudentStatus(String status) { this.status = status; }

}
