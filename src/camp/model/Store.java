package camp.model;

import java.util.ArrayList;
import java.util.List;

public class Store {
    // 과목 타입
    public static final String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    public static final String SUBJECT_TYPE_CHOICE = "CHOICE";

    // index 관리 필드
    private static int studentIndex;
    private static final String INDEX_TYPE_STUDENT = "ST";
    private static int subjectIndex;
    private static final String INDEX_TYPE_SUBJECT = "SU";
    private static int scoreIndex;
    private static final String INDEX_TYPE_SCORE = "SC";

    // 데이터 저장소
    public static List<Student> studentStore = new ArrayList<>();
    public static List<Subject> subjectStore = List.of(
            new Subject(
                    sequence(INDEX_TYPE_SUBJECT),
                    "Java",
                    SUBJECT_TYPE_MANDATORY
            ),
            new Subject(
                    sequence(INDEX_TYPE_SUBJECT),
                    "객체지향",
                    SUBJECT_TYPE_MANDATORY
            ),
            new Subject(
                    sequence(INDEX_TYPE_SUBJECT),
                    "Spring",
                    SUBJECT_TYPE_MANDATORY
            ),
            new Subject(
                    sequence(INDEX_TYPE_SUBJECT),
                    "JPA",
                    SUBJECT_TYPE_MANDATORY
            ),
            new Subject(
                    sequence(INDEX_TYPE_SUBJECT),
                    "MySQL",
                    SUBJECT_TYPE_MANDATORY
            ),
            new Subject(
                    sequence(INDEX_TYPE_SUBJECT),
                    "디자인 패턴",
                    SUBJECT_TYPE_CHOICE
            ),
            new Subject(
                    sequence(INDEX_TYPE_SUBJECT),
                    "Spring Security",
                    SUBJECT_TYPE_CHOICE
            ),
            new Subject(
                    sequence(INDEX_TYPE_SUBJECT),
                    "Redis",
                    SUBJECT_TYPE_CHOICE
            ),
            new Subject(
                    sequence(INDEX_TYPE_SUBJECT),
                    "MongoDB",
                    SUBJECT_TYPE_CHOICE
            )
    );

    public static List<Score> scoreStore = new ArrayList<>();

    //Getter와 Setter
    public static List<Student> getStudentStore() {
        return studentStore;
    }

    public static List<Subject> getSubjectStore() {
        return subjectStore;
    }

    public static List<Score> getScoreStore() {
        return scoreStore;
    }

    public static String sequence(String type) {
        switch (type) {
            case INDEX_TYPE_STUDENT -> {
                studentIndex++;
                return INDEX_TYPE_STUDENT + studentIndex;
            }
            case INDEX_TYPE_SUBJECT -> {
                subjectIndex++;
                return INDEX_TYPE_SUBJECT + subjectIndex;
            }
            default -> {
                scoreIndex++;
                return INDEX_TYPE_SCORE + scoreIndex;
            }
        }
    }
}
