package camp;

import camp.model.Score;
import camp.model.Subject;

import java.util.List;
import java.util.Scanner;

public class ScoreRegistrar {

    private static Scanner scanner;

    // 생성자
    public ScoreRegistrar(Scanner scanner) {
        ScoreRegistrar.scanner = scanner;
    }

    // 수강생의 과목별 시험 회차 및 점수 등록 (정적 메서드로 유지)
    public static void createScore(String studentId, List<Subject> subjectStore, List<Score> scoreStore) {
        System.out.println("시험 점수를 등록합니다...");

        // 과목 입력
        String subjectId;
        while (true) {
            System.out.println("과목 입력: ");
            subjectId = scanner.next();
            if (subjectId.isEmpty()) {
                System.out.println("과목을 다시 입력해주세요.");
            } else {
                break;
            }
        }

        // 회차 입력
        System.out.println("시험 회차 입력(1~10): ");
        int round = scanner.nextInt();
        if (round < 1 || round > 10) {
            System.out.println("회차는 1부터 10까지 입니다.");
            return;
        }

        // 점수 입력
        System.out.println("점수 입력: ");
        int score = scanner.nextInt();
        if (score < 1 || score > 100) {
            System.out.println("점수는 0부터 100까지 입니다.");
            return;
        }

        // 과목 유형 찾기
        Subject subject = findSubjectById(subjectId, subjectStore);
        if (subject == null) {
            System.out.println("유효하지 않은 과목입니다.");
            return;
        }
        String subjectType = subject.getSubjectType();

        // 점수 등록
        Score scoreEntry = new Score(studentId, subjectId, round, score, subjectType);
        scoreStore.add(scoreEntry);
        System.out.println("\n점수 등록 성공!");
    }

    // 수강생의 과목ID로 과목 객체 리턴
    private static Subject findSubjectById(String subjectId, List<Subject> subjectStore) {
        return subjectStore.stream()
                .filter(subject -> subject.getSubjectId().equals(subjectId)).findFirst().orElse(null);
    }
}
