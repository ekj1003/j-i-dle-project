package camp;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Notification
 * Java, 객체지향이 아직 익숙하지 않은 분들은 위한 소스코드 틀입니다.
 * main 메서드를 실행하면 프로그램이 실행됩니다.
 * model 의 클래스들과 아래 (// 기능 구현...) 주석 부분을 완성해주세요!
 * 프로젝트 구조를 변경하거나 기능을 추가해도 괜찮습니다!
 * 구현에 도움을 주기위한 Base 프로젝트입니다. 자유롭게 이용해주세요!
 */
public class CampManagementApplication {
    // 데이터 저장소
    private static List<Student> studentStore;
    private static List<Subject> subjectStore;
    private static List<Score> scoreStore;


    // 과목 타입
    private static String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    private static String SUBJECT_TYPE_CHOICE = "CHOICE";

    // index 관리 필드
    private static int studentIndex;
    private static final String INDEX_TYPE_STUDENT = "ST";
    private static int subjectIndex;
    private static final String INDEX_TYPE_SUBJECT = "SU";
    private static int scoreIndex;
    private static final String INDEX_TYPE_SCORE = "SC";

    // 스캐너
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        setInitData();
        try {
            displayMainView();
        } catch (Exception e) {
            System.out.println("\n오류 발생!\n프로그램을 종료합니다.");
        }
    }

    // 초기 데이터 생성
    private static void setInitData() {
        studentStore = new ArrayList<>();
        subjectStore = List.of(
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
        scoreStore = new ArrayList<>();
    }

    // index 자동 증가
    private static String sequence(String type) {
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

    private static void displayMainView() throws InterruptedException {
        boolean flag = true;
        while (flag) {
            System.out.println("\n===================================");
            System.out.println("내일배움캠프 수강생 관리 프로그램 실행 중...");
            System.out.println("1. 수강생 관리");
            System.out.println("2. 점수 관리");
            System.out.println("3. 프로그램 종료");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> displayStudentView(); // 수강생 관리
                case 2 -> displayScoreView(); // 점수 관리
                case 3 -> flag = false; // 프로그램 종료
                default -> {
                    System.out.println("잘못된 입력입니다.\n되돌아갑니다!");
                    Thread.sleep(2000);
                }
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }

    private static void displayStudentView() {
        boolean flag = true;
        while (flag) {
            System.out.println("===================================");
            System.out.println("수강생 관리 실행 중...");
            System.out.println("1. 수강생 등록");
            System.out.println("2. 수강생 목록 조회");
            System.out.println("3. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createStudent(); // 수강생 등록
                case 2 -> displayStudentInquiry(); // 수강생 목록 조회
                case 3 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }
    //수강생 목록 조회 메뉴
    private static void displayStudentInquiry() {
        boolean flag = true;
        while (flag) {
            System.out.println("===================================");
            System.out.println("수강생 목록 조회 메뉴 실행 중...");
            System.out.println("1. 수강생 전체 정보 조회");
            System.out.println("2. 상태별 수강생 조회");
            System.out.println("3. 이전 화면으로 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> inquireStudent(); // 수강생 등록
                case 2 -> inquireStudentByStatus(); // 수강생 목록 조회
                case 3 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    // 수강생 등록
    private static void createStudent() {
        System.out.println("\n수강생을 등록합니다...");
        System.out.print("수강생 이름 입력: ");
        String studentName = sc.next();
        // 기능 구현 (필수 과목, 선택 과목)

        Student student = new Student(sequence(INDEX_TYPE_STUDENT), studentName); // 수강생 인스턴스 생성 예시 코드
        // 기능 구현
        System.out.println("수강생 등록 성공!\n");
    }

    // 수강생 목록 조회
    private static void inquireStudent() {
        System.out.println("\n수강생 목록을 조회합니다...");
        for (Student student : studentStore) {
            System.out.println(
                    student.getStudentId() +
                            " " +  student.getStudentName() +
                            " [상태:" + student.getStatus() + "]"
            );
        }
        System.out.println("\n수강생 목록 조회 성공!");
    }
    //상태별 수강생 목록 조회
    private static void inquireStudentByStatus() {
        System.out.println("\n 상태별 수강생 목록을 조회합니다...");
        filterAndPrintStudentsByStatus("Red");
        filterAndPrintStudentsByStatus("Green");
        filterAndPrintStudentsByStatus("Yellow");
        System.out.println("\n상태별 수강생 목록 조회 성공!");
    }

    //상태별로 필터링해주는 메서드
    public static void filterAndPrintStudentsByStatus (String status) {
        List<Student> filteredStudents = studentStore.stream()
                .filter(student -> status.equals(student.getStatus()))
                .toList();

        System.out.println("\n상태 : " + status + " 인 학생들");
        for (Student student : filteredStudents) {
            System.out.println(student.getStudentId() + " " +  student.getStudentName() + "" + student.getStatus());
        }
    }

    private static void displayScoreView() {
        boolean flag = true;
        while (flag) {
            System.out.println("====================================");
            System.out.println("점수 관리 실행 중...");
            System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록");
            System.out.println("2. 수강생의 과목별 회차 점수 수정");
            System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
            System.out.println("4. 수강생의 과목별 평균 등급 조회");
            System.out.println("5. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> updateRoundScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                case 3 -> inquireRoundGradeBySubject(); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> inquireAVGGradeBySubject(); //수강생의 과목별 평균 등급 조회
                case 5 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }
    // 수강생의 ID 찾기
    private static String getStudentId() {
        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        return sc.next();
    }

    // 수강생의 ID로 학생 객체 리턴
    public static Student findStudent(String input) {
        return studentStore.stream()
                .filter(student -> student.getStudentId().equals(input))
                .findFirst().orElse(null);
    }

    // 수강생의 과목별 시험 회차 및 점수 등록
    private static void createScore() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        System.out.println("시험 점수를 등록합니다...");
        // 기능 구현
        System.out.println("\n점수 등록 성공!");
    }

    // 수강생의 과목별 회차 점수 수정
    private static void updateRoundScoreBySubject() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        // 기능 구현 (수정할 과목 및 회차, 점수)
        System.out.println("시험 점수를 수정합니다...");
        // 기능 구현
        System.out.println("\n점수 수정 성공!");
    }

    // 수강생의 특정 과목 회차별 등급 조회
    private static void inquireRoundGradeBySubject() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        // 기능 구현 (조회할 특정 과목)
        System.out.println("회차별 등급을 조회합니다...");
        // 기능 구현
        System.out.println("\n등급 조회 성공!");
    }

    // 수강생의 과목별 평균 등급 조회 최종 메서드
    public static void inquireAVGGradeBySubject() {

        String input = getStudentId();
        Student who = findStudent(input);
        studentAverageGradeBySubject(who);

    }

    // 수강생 과목별 평균 등급 조회 코어 메서드
    public static void studentAverageGradeBySubject(Student student) {
        System.out.println( student.getStudentName()+"님의 과목별 평균 등급은 다음과 같습니다..");
        /* *
         필수과목, 선택과목 등급 산정 기준이 다름
         필수과목 평균 + 선택과목 평균 내서 붙여버리기
         */
        listStudentSubjectByType(student, "MANDATORY");
        listStudentSubjectByType(student, "CHOICE");

        printAVGGradebySubject(student,"MANDATORY");
        printAVGGradebySubject(student,"CHOICE");

    }
    //수강생의 해당 과목 평균 등급을 반환
    public static char filterAndReturnAverageGradeByStudentandSubject(String studentId, String subjectId, String subjectTypeLabel) {
        List<Score> filteredScore = scoreStore.stream()
                .filter(score -> studentId.equals(score.getStudentId()) && subjectId.equals(score.getSubjectId()))
                .toList();
        double result = 0;
        double average = 0;

        for (Score score : filteredScore) {
            result+=score.getScore();
        }
        average = result/10;
        return getGrade(average,subjectTypeLabel);
    }
    //수강생이 듣는 과목을 (전공,선택)에 따라 리스트로 반환
    public static List<Subject> listStudentSubjectByType(Student student , String type) {
        List<Subject> subjectList = student.getSubjectList();
        return subjectList.stream()
                .filter(s -> type.equals(s.getSubjectType())).toList();
    }
    //수강생이 듣는 과목별로 평균등급을 출력해주는 메서드
    public static void printAVGGradebySubject (Student student, String subjectTypeLabel) {
        for (Subject subject : listStudentSubjectByType(student, subjectTypeLabel)) {
            String stid = student.getStudentId();
            String sName = subject.getSubjectName();
            String sId = subject.getSubjectId();
            String simpleSubjectType;
            if (Objects.equals(subjectTypeLabel, "MANDATORY")) {simpleSubjectType = "(필)";} else {simpleSubjectType = "(선)";}
            char grade = filterAndReturnAverageGradeByStudentandSubject(stid, sId, subjectTypeLabel);
            System.out.printf("[ %s %s : %c ]\n",simpleSubjectType,sName,grade);
        }
    }
    //점수를 (전공,선택)에 따라 등급으로 변환해주는 메서드
    private static char getGrade(double result, String subjectTypeLabel) {
        //subjecttype이 1인 경우는 필수과목, 2인 경우는 선택과목
        char Grade = 'N';
        if (subjectTypeLabel == "MANDATORY") {
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
            } else {
                Grade = 'N';
            }
        } else if (subjectTypeLabel == "CHOICE") {
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
            } else {
                Grade = 'N';
            }
        } return Grade;

    }

}
