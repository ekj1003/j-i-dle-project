package camp;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;

import java.util.*;

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
            System.out.println("2. 수강생 목록 조회 메뉴");
            System.out.println("3. 수강생 상태 수정"); // 추가 기능) 수강생 상태 수정
            System.out.println("4. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createStudent(); // 수강생 등록
                case 2 -> displayStudentInquiry(); // 수강생 목록 조회
                case 3 -> updateStudentStatus(); // 수강생 상태 수정
                case 4 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    // 수강생 상태 수정
    private static void updateStudentStatus() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        System.out.print("수정할 수강생의 상태를 입력: ");
        String newStatus = sc.next();
        for(Student student : studentStore) {
            if (student.getStudentId().equals(studentId)) student.setStudentStatus(newStatus);
        }
        System.out.println("수강생 상태 수정 성공!\n");
    }

    // 수강생 등록
    private static void createStudent() {
        System.out.println("\n수강생을 등록합니다...");
        System.out.print("수강생 이름 입력: ");
        String studentName = sc.next();


        System.out.println("좋음 : green");
        System.out.println("중간 : yellow");
        System.out.println("나쁨 : red");
        System.out.println("수강생의 상태를 입력해주세요 : ");
        String status = sc.next();

        sc.nextLine(); // 입력 버퍼 비우기
        // 기능 구현 (필수 과목, 선택 과목)
        String selectsubject;
        while (true) {
            System.out.println("필수과목 최소 3개 이상 입력해주세요(띄어쓰기로 구분)");
            System.out.println("Java, 객체지향, Spring, JPA, MySQL");
            System.out.print("필수과목 입력 : ");
            selectsubject = sc.nextLine();
            // 간단한 유효성 검사 (최소 3개 입력 여부 확인)
            if (selectsubject.split(" ").length >= 3) {
                break;
            } else {
                System.out.println("필수 과목은 최소 3개 이상 입력해야 합니다. 다시 입력해주세요.");
            }
        }
        String choicesubject;
        while (true) {
            System.out.println("선택과목 최소 2개 이상 입력해주세요(띄어쓰기로 구분)");
            System.out.println("디자인 패턴, SpringSecurity, Redis, MongoDB");
            System.out.print("선택과목 입력 : ");
            choicesubject = sc.nextLine();
            // 간단한 유효성 검사 (최소 2개 입력 여부 확인)
            if (choicesubject.split(" ").length >= 2) {
                break;
            } else {
                System.out.println("선택 과목은 최소 2개 이상 입력해야 합니다. 다시 입력해주세요.");
            }
        }

        Student student = new Student(sequence(INDEX_TYPE_STUDENT), studentName); // 수강생 인스턴스 생성 예시 코드

        // 기능 구현
        List<String> subjectIds = new ArrayList<>();
        for (String subjectName : selectsubject.split(" ")) {
            Subject subject = subjectStore.stream()
                    .filter(s -> s.getSubjectName().equals(subjectName))
                    .findFirst().orElse(null);
            if (subject != null) {
                subjectIds.add(subject.getSubjectId());
            }
        }

        for (String subjectName : choicesubject.split(" ")) {
            Subject subject = subjectStore.stream()
                    .filter(s -> s.getSubjectName().equals(subjectName))
                    .findFirst().orElse(null);
            if (subject != null) {
                subjectIds.add(subject.getSubjectId());
            }
        }

        student.setStudentStatus(status);


        studentStore.add(student); // 학생 저장


        System.out.println("수강생 이름 : " + studentName);
        System.out.println("수강생 ID : " + student);
        System.out.println("수강생 상태 : " + status);
        System.out.println("수강목록 : " + selectsubject + choicesubject);
        System.out.println("수강목록 ID : " + subjectIds);
        System.out.println("수강생 등록 성공!\n");
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
                case 1 -> inquireStudent(); // 수강생 전체 목록 조회
                case 2 -> inquireStudentByStatus(); // 상태별 수강생 목록 조회
                case 3 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    // 수강생 목록 조회
    private static void inquireStudent() {
        System.out.println("\n수강생 목록을 조회합니다...");
        for (Student student : studentStore) {
            System.out.print(
                    student.getStudentId() +
                            " " +  student.getStudentName() +
                            " [상태:" + student.getStatus() + "]");
            printStudentSubjects(student);
        }
        System.out.println("\n수강생 목록 조회 성공!");
    }

    //수강생이 듣는 과목을 (필수,선택)으로 나눠서 출력
    private static void printStudentSubjects (Student student) {
        System.out.print(" [수강 과목:(필)");
        for (Subject subject : listStudentSubjectByType(student, "MANDATORY")) {
            System.out.printf("%s,",subject.getSubjectName());
        }
        System.out.print("(선)");
        for (Subject subject : listStudentSubjectByType(student, "CHOICE")) {
            System.out.printf("%s,",subject.getSubjectName());
        }
        System.out.print("]");
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
    private static void filterAndPrintStudentsByStatus (String status) {
        List<Student> filteredStudents = studentStore.stream()
                .filter(student -> status.equals(student.getStatus()))
                .toList();

        System.out.println("\n상태 : " + status + " 인 학생들");
        for (Student student : filteredStudents) {
            System.out.println(student.getStudentId() + " " +  student.getStudentName());
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
            System.out.println("5. 수강생의 특정 과목 회차별 등급 조회");
            System.out.println("6. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> updateRoundScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                case 3 -> inquireRoundGradeBySubject(); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> inquireAVGGradeBySubject(); //수강생의 과목별 평균 등급 조회
                case 5 -> inquireAvgGradeByMandatorySubject(); // 특정 상태 수강생들의 필수 과목 평균 등급 조회
                case 6 -> flag = false; // 메인 화면 이동
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
    private static Student findStudent(String input) {
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
        System.out.print("수정할 과목 입력: ");
        String subjectName = sc.next();
        System.out.print("수정할 회차 입력: ");
        int round = sc.nextInt();
        System.out.println("수정할 점수 입력: ");
        int newScore = sc.nextInt();

        String subjectId="";

        for(Subject subject : subjectStore) {
            if(subject.getSubjectName().equals(subjectName)) subjectId = subject.getSubjectId();
        }

        System.out.println("시험 점수를 수정합니다...");
        // 기능 구현
        for(Score score : scoreStore) {
            if(score.getStudentId().equals(studentId) && score.getRound() == round && score.getSubjectId().equals(subjectId)) {
                score.setScore(newScore);
            }
        }
        System.out.println("\n점수 수정 성공!");
    }

    // 수강생의 특정 과목 회차별 등급 조회
    private static void inquireRoundGradeBySubject() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호

        // 기능 구현 (조회할 특정 과목)
        System.out.print("조회할 과목의 번호를 입력하시오...");
        String subjectId = sc.next();
        Subject subject = subjectStore.stream().filter(s -> s.getSubjectId().equals(subjectId)).findFirst().get();

        System.out.println(subject.getSubjectName() + " 과목 회차별 등급을 조회합니다...");
        System.out.println("====================================");

        List<Score> scoreList = new ArrayList<>(scoreStore.stream()
                .filter(s -> s.getStudentId().equals(studentId) && s.getSubjectId().equals(subjectId))
                .toList());

        scoreList.sort(Comparator.comparingInt(Score::getRound));

        for (Score score : scoreList) {
            System.out.println("회차 = " + score.getRound());
            System.out.println("등급 = " + score.getGrade());
            System.out.println("====================================");
        }
        // 기능 구현
        System.out.println("\n등급 조회 성공!");
    }

    // 특정 상태 수강생들의 필수 과목 평균 등급 조회
    private static void inquireAvgGradeByMandatorySubject() {

        System.out.print("조회할 수강생의 상태를 입력하시오...");
        String status = sc.next();
        List<Student> students = studentStore.stream().
                filter(s -> s.getStatus().equals(status)).toList();

        System.out.println(status + " 상태 수강생들의 필수 과목 평균 등급을 조회합니다...");
        System.out.println("====================================");

        for (Student student : students) {
            List<String> subjectList = student.getSubjectList();

            for (String subjectId : subjectList) {
                Subject subject = subjectStore.stream()
                        .filter(s -> s.getSubjectId().equals(subjectId)).findFirst().get();

                if (subject.getSubjectType().equals(SUBJECT_TYPE_MANDATORY)) {
                    List<Integer> scoreList = scoreStore.stream().filter(s -> s.getStudentId().equals(student.getStudentId()) &&
                            s.getSubjectId().equals(subjectId)).map(Score::getScore).toList();

                    char avgGrade = getAvgGrade(scoreList);
                    System.out.println(student.getStudentName() + " 수강생 " + subject.getSubjectName() + " 과목 평균 등급 = " + avgGrade);
                }
            }
            System.out.println("====================================");
        }
    }

    // 점수 리스트를 받아 평균 등급을 반환
    private static char getAvgGrade(List<Integer> scoreList) {
        int scoreSum = scoreList.stream().reduce(0, Integer::sum);
        int avgSum = scoreSum / scoreList.size();
        return getGrade(avgSum, SUBJECT_TYPE_MANDATORY);
    }
    // 수강생의 과목별 평균 등급 조회 최종 메서드
    private static void inquireAVGGradeBySubject() {

        String input = getStudentId();
        Student who = findStudent(input);
        studentAverageGradeBySubject(who);

    }

    // 수강생 과목별 평균 등급 조회 코어 메서드
    private static void studentAverageGradeBySubject(Student student) {
        System.out.println( student.getStudentName()+"님의 과목별 평균 등급은 다음과 같습니다..");
        /* *
         필수과목, 선택과목 등급 산정 기준이 다름
         필수과목 평균 + 선택과목 평균 내서 붙여버리기
         */

        //여기서 동작하지는 않지만, 같은 student 객체를 쓰기 위해 여기에 넣었습니다.
        listStudentSubjectByType(student, "MANDATORY");
        listStudentSubjectByType(student, "CHOICE");

        printAVGGradebySubject(student,"MANDATORY");
        printAVGGradebySubject(student,"CHOICE");

    }
    //수강생의 해당 과목 평균 등급을 반환
    private static char filterAndReturnAverageGradeByStudentandSubject(String studentId, String subjectId, String subjectTypeLabel) {
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
    private static List<Subject> listStudentSubjectByType(Student student , String type) {
        List<Subject> subjectList = student.getSubjectListTypeSubject();
        return subjectList.stream()
                .filter(s -> type.equals(s.getSubjectType())).toList();
    }
    //수강생이 듣는 과목별로 평균등급을 출력해주는 메서드
    private static void printAVGGradebySubject (Student student, String subjectTypeLabel) {
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
