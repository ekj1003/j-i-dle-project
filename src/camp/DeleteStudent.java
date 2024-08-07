package camp;

import camp.model.Student;
import camp.model.Score;

import java.util.List;

public class DeleteStudent {
    private List<Student> studentStore;
    private List<Score> scoreStore;

    public DeleteStudent(List<Student> studentStore, List<Score> scoreStore) {
        this.studentStore = studentStore;
        this.scoreStore = scoreStore;
    }

    public void deleteStudent(String studentId) {
        System.out.println("\n수강생을 삭제합니다...");

        // 학생 삭제
        boolean studentRemoved = studentStore.removeIf(student -> student.getStudentId().equals(studentId));

        // 관련 점수 삭제
        scoreStore.removeIf(score -> score.getStudentId().equals(studentId));

        if (studentRemoved) {
            System.out.println("수강생 삭제 완료");
        } else {
            System.out.println("해당 ID를 가진 수강생이 없습니다.");
        }
    }
}
