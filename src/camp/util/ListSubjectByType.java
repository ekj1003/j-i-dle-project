package camp.util;

import camp.model.Student;
import camp.model.Subject;

import java.util.ArrayList;
import java.util.List;

import static camp.util.FindSubjectById.findSubjectById;

public class ListSubjectByType {
    //(필수,선택)별로 과목 리스트 생성
    //String subjectType은 "MANDATORY" 혹은 "CHOICE"
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
