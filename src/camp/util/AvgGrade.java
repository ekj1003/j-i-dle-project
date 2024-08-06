package camp.util;

import camp.CampManagementApplication;
import camp.model.Score;

import java.util.List;

public class AvgGrade {
    //수강생의 해당 과목 평균 등급을 반환
    public static char avgGrade(String studentId, String subjectId, String subjectTypeLabel) {
        List<Score> filteredScore = CampManagementApplication.scoreStore.stream()
                .filter(score -> studentId.equals(score.getStudentId()) && subjectId.equals(score.getSubjectId()))
                .toList();
        double result = 0;
        double average = 0;

        for (Score score : filteredScore) {
            result += score.getScore();
        }
        average = result / filteredScore.size();
        return GetGrade.getGrade(average, subjectTypeLabel);
    }
}
