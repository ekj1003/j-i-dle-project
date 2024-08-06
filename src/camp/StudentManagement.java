package camp;

import camp.model.Student;
import camp.model.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManagement {

    private static List<Student> studentStore;
    private static List<Subject> subjectStore;
    private static String sequencePrefix;

    // 데이터 설정
    public static void setData(List<Student> students, List<Subject> subjects, String prefix) {
        studentStore = students;
        subjectStore = subjects;
        sequencePrefix = prefix;
    }

    public static void createStudent() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n수강생을 등록합니다...");
        System.out.print("수강생 이름 입력: ");
        String studentName = sc.next().trim();


        System.out.println("좋음 : Green");
        System.out.println("중간 : Yellow");
        System.out.println("나쁨 : Red");
        System.out.println("수강생의 상태를 입력해주세요 : ");
        String status = sc.next().trim();

        sc.nextLine(); //버퍼 지우기

        // 기능 구현 (필수 과목, 선택 과목)
        String mandatorysubject;
        while (true) {
            System.out.println("필수과목 최소 3개 이상 입력해주세요(,띄어쓰기로 구분)");
            System.out.println("Java, 객체지향, Spring, JPA, MySQL");
            System.out.print("필수과목 입력 : ");
            mandatorysubject = sc.nextLine().trim();
            // 간단한 유효성 검사 (최소 3개 입력 여부 확인)
            if (mandatorysubject.split(", ").length >= 3) {
                break;
            } else {
                System.out.println("필수 과목은 최소 3개 이상 입력해야 합니다. 다시 입력해주세요.");
            }
        }
        String choicesubject;
        while (true) {
            System.out.println("선택과목 최소 2개 이상 입력해주세요(,띄어쓰기로 구분)");
            System.out.println("디자인 패턴, Spring Security, Redis, MongoDB");
            System.out.print("선택과목 입력 : ");
            choicesubject = sc.nextLine().trim();
            // 간단한 유효성 검사 (최소 2개 입력 여부 확인)
            if (choicesubject.split(", ").length >= 2) {
                break;
            } else {
                System.out.println("선택 과목은 최소 2개 이상 입력해야 합니다. 다시 입력해주세요.");
            }
        }

        Student student = new Student(CampManagementApplication.generateStudentId(), studentName); // 수강생 인스턴스 생성 예시 코드

        // 기능 구현
        List<String> subjectIds = new ArrayList<>();
        for (String subjectName : mandatorysubject.split(", ")) {
            Subject subject = subjectStore.stream()
                    .filter(s -> s.getSubjectName().equalsIgnoreCase(subjectName))
                    .findFirst().orElse(null);
            if (subject != null) {
                subjectIds.add(subject.getSubjectId());
            }
        }

        for (String subjectName : choicesubject.split(", ")) {
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
        System.out.println("수강목록 : " + mandatorysubject + ", " + choicesubject);
        System.out.println("수강목록 ID : " + student.getSubjectList());
        System.out.println("수강생 등록 성공!\n");
    }

}



