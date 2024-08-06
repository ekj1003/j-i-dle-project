package camp.util;

import camp.CampManagementApplication;
import camp.model.Subject;

public class FindSubjectById {
    //수강생이 듣는 과목 ID로 과목 객체 리턴
    public static Subject findSubjectById(String subjectId) {
        return CampManagementApplication.subjectStore.stream()
                .filter(subject -> subject.getSubjectId().equals(subjectId))
                .findFirst()
                .orElse(null);
    }
}
