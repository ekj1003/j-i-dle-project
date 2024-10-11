package camp;

import java.util.Scanner;

/**
 * Notification
 * Java, 객체지향이 아직 익숙하지 않은 분들은 위한 소스코드 틀입니다.
 * main 메서드를 실행하면 프로그램이 실행됩니다.
 * model 의 클래스들과 아래 (// 기능 구현...) 주석 부분을 완성해주세요!
 * 프로젝트 구조를 변경하거나 기능을 추가해도 괜찮습니다!
 * 구현에 도움을 주기위한 Base 프로젝트입니다. 자유롭게 이용해주세요!
 */

public class CampManagementApplication {
    // 스캐너
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            displayMainView();
        } catch (Exception e) {
            System.out.println("\n오류 발생!\n프로그램을 종료합니다.");
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
            System.out.println("4. 수강생 삭제"); // 수강생 삭제 추가
            System.out.println("5. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> StudentManagement.createStudent(); // 수강생 등록
                case 2 -> displayStudentInquiry(); // 수강생 목록 조회
                case 3 -> StudentManagement.updateStudentStatus(); // 수강생 상태 수정
                case 4 -> StudentManagement.deleteStudent(); //수강생 삭제
                case 5 -> flag = false; // 메인 화면 이동
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
                case 1 -> StudentManagement.inquireStudent(); // 수강생 전체 목록 조회
                case 2 -> StudentManagement.inquireStudentByStatus(); // 상태별 수강생 목록 조회
                case 3 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    //점수 관리 메뉴
    private static void displayScoreView() {
        boolean flag = true;
        while (flag) {
            System.out.println("====================================");
            System.out.println("점수 관리 실행 중...");
            System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록");
            System.out.println("2. 수강생의 과목별 회차 점수 수정");
            System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
            System.out.println("4. 수강생의 과목별 평균 등급 조회");
            System.out.println("5. 특정 상태 수강생들의 필수 과목 평균 등급 조회");
            System.out.println("6. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> ScoreManagement.createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> ScoreManagement.updateRoundScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                case 3 -> ScoreManagement.inquireRoundGradeBySubject(); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> ScoreManagement.inquireAVGGradeBySubject(); //수강생의 과목별 평균 등급 조회
                case 5 -> ScoreManagement.inquireAvgGradeByMandatorySubject(); // 특정 상태 수강생들의 필수 과목 평균 등급 조회
                case 6 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }
}
