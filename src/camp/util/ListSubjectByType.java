package camp.util;

import camp.model.Student;
import camp.model.Subject;

import java.util.ArrayList;
import java.util.List;

public class ListSubjectByType {
    public static List<Subject> listStudentSubjectByType(Student student, String subjectType) {
        List<Subject> subjectList = new ArrayList<>();

        for (String subjectId : student.getSubjectList()) {
            subjectList.add(findSubjectById(subjectId));
        }

        return subjectList.stream()
                .filter(s -> subjectType.equals(s.getSubjectType()))
                .toList();
    }
}
