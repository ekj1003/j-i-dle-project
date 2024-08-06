package camp.util;

import java.util.Objects;

public class GetGrade {
    public static char getGrade(double result, String subjectTypeLabel) {
        char Grade = 'N';
        if (Objects.equals(subjectTypeLabel, "MANDATORY")) {
            if (result >= 95) {
                Grade = 'A';
            } else if (result >= 90) {
                Grade = 'B';
            } else if (result >= 80) {
                Grade = 'C';
            } else if (result >= 70) {
                Grade = 'D';
            } else if (result >= 60) {
                Grade = 'F';
            }
        } else if (Objects.equals(subjectTypeLabel, "CHOICE")) {
            if (result >= 90) {
                Grade = 'A';
            } else if (result >= 80) {
                Grade = 'B';
            } else if (result >= 70) {
                Grade = 'C';
            } else if (result >= 60) {
                Grade = 'D';
            } else if (result >= 50) {
                Grade = 'F';
            }
        }
        return Grade;

    }
}
