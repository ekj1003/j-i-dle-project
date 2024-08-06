package camp;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;

import java.util.*;

import static camp.Store.*;
import static camp.SubjectStore.SUBJECT_TYPE_MANDATORY;


/**
 * Notification
 * Java, 객체지향이 아직 익숙하지 않은 분들은 위한 소스코드 틀입니다.
 * main 메서드를 실행하면 프로그램이 실행됩니다.
 * model 의 클래스들과 아래 (// 기능 구현...) 주석 부분을 완성해주세요!
 * 프로젝트 구조를 변경하거나 기능을 추가해도 괜찮습니다!
 * 구현에 도움을 주기위한 Base 프로젝트입니다. 자유롭게 이용해주세요!
 */
public class CampManagementApplication {
    public static void displayMainView() throws InterruptedException {
        boolean flag = true;
        while (flag) {
            System.out.println("\n===================================");
            System.out.println("내일배움캠프 수강생 관리 프로그램 실행 중...");
            System.out.println("1. 수강생 관리");
            System.out.println("2. 점수 관리");
            System.out.println("3. 프로그램 종료");
            System.out.print("관리 항목을 선택하세요...");
            int input = util.sc.nextInt();

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

    public static void displayStudentView() {
        boolean flag = true;
        while (flag) {
            System.out.println("===================================");
            System.out.println("수강생 관리 실행 중...");
            System.out.println("1. 수강생 등록");
            System.out.println("2. 수강생 목록 조회 메뉴");
            System.out.println("3. 수강생 상태 수정"); // 추가 기능) 수강생 상태 수정
            System.out.println("4. 수강생 삭제"); // 수강생 삭제 추가
            System.out.println("5. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = util.sc.nextInt();

            switch (input) {
                case 1 -> createStudent(); // 수강생 등록
                case 2 -> displayStudentInquiry(); // 수강생 목록 조회
                case 3 -> updateStudentStatus(); // 수강생 상태 수정
                case 4 -> deleteStudent(); //수강생 삭제
                case 5 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    // 수강생 상태 수정
    public static void updateStudentStatus() {
        String studentId = util.getStudentId(); // 관리할 수강생 고유 번호
        System.out.print("수정할 수강생의 상태를 입력: ");
        String newStatus = util.sc.next();
        for(Student student : studentStore) {
            if (student.getStudentId().equals(studentId)) student.setStudentStatus(newStatus);
        }
        System.out.println("수강생 상태 수정 성공!\n");
    }

    // 수강생 등록
    public static void createStudent() {
        System.out.println("\n수강생을 등록합니다...");
        System.out.print("수강생 이름 입력: ");
        String studentName = util.sc.next();


        System.out.println("좋음 : Green");
        System.out.println("중간 : Yellow");
        System.out.println("나쁨 : Red");
        System.out.println("수강생의 상태를 입력해주세요 : ");
        String status = util.sc.next();

        util.sc.nextLine(); // 입력 버퍼 비우기

        // 기능 구현 (필수 과목, 선택 과목)
        String mandatorysubject;
        while (true) {
            System.out.println("필수과목 최소 3개 이상 입력해주세요(띄어쓰기로 구분)");
            System.out.println("Java, 객체지향, Spring, JPA, MySQL");
            System.out.print("필수과목 입력 : ");
            mandatorysubject = util.sc.nextLine();
            // 간단한 유효성 검사 (최소 3개 입력 여부 확인)
            if (mandatorysubject.split(" ").length >= 3) {
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
            choicesubject = util.sc.nextLine();
            // 간단한 유효성 검사 (최소 2개 입력 여부 확인)
            if (choicesubject.split(" ").length >= 2) {
                break;
            } else {
                System.out.println("선택 과목은 최소 2개 이상 입력해야 합니다. 다시 입력해주세요.");
            }
        }

        Student student = new Student(Store.sequence(INDEX_TYPE_STUDENT), studentName); // 수강생 인스턴스 생성 예시 코드

        // 기능 구현
        List<String> subjectIds = new ArrayList<>();
        for (String subjectName : mandatorysubject.split(" ")) {
            Subject subject = subjectStore.stream()
                    .filter(s -> s.getSubjectName().equalsIgnoreCase(subjectName))
                    .findFirst().orElse(null);
            if (subject != null) {
                subjectIds.add(subject.getSubjectId());
            }
        }

        for (String subjectName : choicesubject.split(" ")) {
            Subject subject = subjectStore.stream()
                    .filter(s -> s.getSubjectName().equalsIgnoreCase(subjectName))
                    .findFirst().orElse(null);
            if (subject != null) {
                subjectIds.add(subject.getSubjectId());
            }
        }

        student.setStudentStatus(status);
        student.setSubjectList(subjectIds);


        studentStore.add(student); // 학생 저장


        System.out.println("수강생 이름 : " + student.getStudentName());
        System.out.println("수강생 ID : " + student.getStudentId());
        System.out.println("수강생 상태 : " + status);
        System.out.println("수강목록 : " + mandatorysubject + " " + choicesubject);
        System.out.println("수강목록 ID : " + student.getSubjectList());
        System.out.println("수강생 등록 성공!\n");
    }

    //수강생 목록 조회 메뉴
    public static void displayStudentInquiry() {
        boolean flag = true;
        while (flag) {
            System.out.println("===================================");
            System.out.println("수강생 목록 조회 메뉴 실행 중...");
            System.out.println("1. 수강생 전체 정보 조회");
            System.out.println("2. 상태별 수강생 조회");
            System.out.println("3. 이전 화면으로 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = util.sc.nextInt();

            switch (input) {
                case 1 -> inquireStudents.inquireStudent(); // 수강생 전체 목록 조회
                case 2 -> inquireStudents.inquireStudentByStatus(); // 상태별 수강생 목록 조회
                case 3 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    public static void displayScoreView() {
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
            int input = util.sc.nextInt();

            switch (input) {
                case 1 -> createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> updateRoundScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                case 3 -> inquireRoundGradeBySubject(); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> inquireAvgGradeBySubject.mInquireAvgGradeBySubject(); //수강생의 과목별 평균 등급 조회
                case 5 -> inquireAvgGradeByMandatorySubject(); // 특정 상태 수강생들의 필수 과목 평균 등급 조회
                case 6 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

        // 수강생의 과목별 시험 회차 및 점수 등록
    public static void createScore() {
        String studentId = util.getStudentId(); // 관리할 수강생 고유 번호
        System.out.println("시험 점수를 등록합니다...");
        // 기능 구현
        System.out.println("\n점수 등록 성공!");
    }

    // 수강생의 과목별 회차 점수 수정
    public static void updateRoundScoreBySubject() {
        String studentId = util.getStudentId(); // 관리할 수강생 고유 번호
        // 기능 구현 (수정할 과목 및 회차, 점수)
        System.out.print("수정할 과목 입력: ");
        String subjectName = util.sc.next();
        System.out.print("수정할 회차 입력: ");
        int round = util.sc.nextInt();
        System.out.println("수정할 점수 입력: ");
        int newScore = util.sc.nextInt();

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
    public static void inquireRoundGradeBySubject() {
        String studentId = util.getStudentId(); // 관리할 수강생 고유 번호

        // 기능 구현 (조회할 특정 과목)
        System.out.print("조회할 과목의 번호를 입력하시오...");
        String subjectId = util.sc.next();
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
    public static void inquireAvgGradeByMandatorySubject() {

        System.out.print("조회할 수강생의 상태를 입력하시오...");
        String status = util.sc.next();
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
    public static char getAvgGrade(List<Integer> scoreList) {
        int scoreSum = scoreList.stream().reduce(0, Integer::sum);
        int avgSum = scoreSum / scoreList.size();
        return util.getGrade(avgSum, SUBJECT_TYPE_MANDATORY);
    }

    // 수강생 삭제
    public static void deleteStudent() {
        System.out.println("\n수강생을 삭제합니다...");
        String studentId = util.getStudentId();

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
