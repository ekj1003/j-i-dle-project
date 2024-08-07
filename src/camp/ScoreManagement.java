package camp;

import camp.model.Score;
import camp.model.Store;
import camp.model.Student;
import camp.model.Subject;

import java.util.*;

public class ScoreManagement {

    private static final Scanner sc = new Scanner(System.in);

    // 수강생의 과목별 회차 점수 수정
    public static void updateRoundScoreBySubject() {
        String studentId = Util.getStudentId(); // 관리할 수강생 고유 번호
        // 기능 구현 (수정할 과목 및 회차, 점수)
        System.out.print("수정할 과목 ID 입력: ");
        String subjectId = sc.next();
        System.out.print("수정할 회차 입력: ");
        int round = sc.nextInt();
        System.out.println("수정할 점수 입력: ");
        int newScore = sc.nextInt();

        System.out.println("시험 점수를 수정합니다...");
        // 기능 구현
        for(Score score : Store.scoreStore) {
            if(score.getStudentId().equals(studentId) && score.getRound() == round && score.getSubjectId().equals(subjectId)) {
                score.setScore(newScore);
            }
        }
        System.out.println("\n점수 수정 성공!");
    }

    // 수강생의 과목별 평균 등급 조회
    public static void inquireAVGGradeBySubject() {
        String input = Util.getStudentId();
        Student student = Util.findStudent(input);
        System.out.println( student.getStudentName()+"님의 과목별 평균 등급은 다음과 같습니다..");
        printAVGGradebySubject(student,"MANDATORY");
        printAVGGradebySubject(student,"CHOICE");
    }
    //수강생이 듣는 과목별로 평균등급을 출력해주는 메서드
    public static void printAVGGradebySubject (Student student, String subjectTypeLabel) {
        for (Subject subject : Util.listStudentSubjectByType(student, subjectTypeLabel)) {
            String stid = student.getStudentId();
            String sName = subject.getSubjectName();
            String sId = subject.getSubjectId();
            String simpleSubjectType;
            if (Objects.equals(subjectTypeLabel, "MANDATORY")) {simpleSubjectType = "(필)";} else {simpleSubjectType = "(선)";}
            char grade = Util.getAvgGradeBySubject(stid, sId, subjectTypeLabel);
            System.out.printf("[ %s %s : %c ]\n",simpleSubjectType,sName,grade);
        }
    }

    // 수강생의 특정 과목 회차별 등급 조회
    public static void inquireRoundGradeBySubject() {
        String studentId = Util.getStudentId(); // 관리할 수강생 고유 번호

        // 조회할 과목 ID 입력
        System.out.print("조회할 과목의 번호를 입력하시오...");
        String subjectId = sc.next();
        Subject subject = Util.findSubjectById(subjectId);

        System.out.println(subject.getSubjectName() + " 과목 회차별 등급을 조회합니다...");
        System.out.println("====================================");

        List<Score> scoreList = Util.getScoreListBySubjectId(studentId, subjectId);
        scoreList.sort(Comparator.comparingInt(Score::getRound)); // 회차 오름차순으로 정렬

        for (Score score : scoreList) {
            System.out.println("회차 = " + score.getRound());
            System.out.println("등급 = " + score.getGrade() + "\n");
        }
        System.out.println("등급 조회 성공!");
    }

    // 특정 상태 수강생들의 필수 과목 평균 등급 조회
    public static void inquireAvgGradeByMandatorySubject() {

        System.out.print("조회할 수강생의 상태를 입력하시오...");
        String status = sc.next();
        List<Student> students = Util.findStudentByStatus(status);

        System.out.println(status + " 상태 수강생들의 필수 과목 평균 등급을 조회합니다...");
        System.out.println("====================================");

        for (Student student : students) {
            List<Subject> subjectList = Util.listStudentSubjectByType(student, Store.SUBJECT_TYPE_MANDATORY);
            System.out.println("\n" + student.getStudentName() + " 수강생 필수 과목 평균 등급");
            for (Subject subject : subjectList) {
                char avgGrade = Util.getAvgGradeBySubject(student.getStudentId(), subject.getSubjectId(), Store.SUBJECT_TYPE_MANDATORY);
                System.out.println(subject.getSubjectName() + " 과목 평균 등급 = " + avgGrade);
            }
        }
        System.out.println("\n등급 조회 성공!");
    }

    // 수강생의 과목별 시험 회차 및 점수 등록
    public static void createScore() {
        System.out.println("시험 점수를 등록합니다...");

        String studentId = Util.getStudentId(); // 관리할 수강생 고유 번호

        // 과목 입력
        String subjectId;
        while (true) {
            System.out.println("과목 ID를 입력하세요: ");
            subjectId = sc.next();
            if (subjectId.isEmpty()) {
                System.out.println("과목을 다시 입력해주세요.");
            } else {
                break;
            }
        }

        // 회차 입력
        System.out.println("시험 회차 입력(1~10): ");
        int round = sc.nextInt();
        if (round < 1 || round > 10) {
            System.out.println("회차는 1부터 10까지 입력 가능합니다.");
            return;
        }

        // 점수 입력
        System.out.println("점수 입력(0~100): ");
        int score = sc.nextInt();
        if (score < 0 || score > 100) {
            System.out.println("점수는 0부터 100까지 입력 가능합니다.");
            return;
        }

        // 과목 유형 찾기
        Subject subject = Util.findSubjectById(subjectId);
        if (subject == null) {
            System.out.println("유효하지 않은 과목입니다.");
            return;
        }
        String subjectType = subject.getSubjectType();

        // 점수 등록
        Score scoreEntry = new Score(studentId, subjectId, round, score, subjectType);
        Store.scoreStore.add(scoreEntry);
        System.out.println("\n점수 등록 성공!");
    }

}


