package camp;

import camp.model.Student;
import camp.model.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManagement {

    private static List<Student> studentStore;
    private static List<Subject> subjectStore;

    private static Scanner sc = new Scanner(System.in);


    // 데이터 설정
    public static void setData(List<Student> students, List<Subject> subjects) {
        studentStore = students;
        subjectStore = subjects;

    }

    public static void createStudent() {

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

    // 수강생 목록 조회
    public static void inquireStudent() {
        System.out.println("\n수강생 목록을 조회합니다...");
        for (Student student : CampManagementApplication.getStudentStore()) {
            System.out.printf("%s ",student.getStudentId());
            System.out.printf("%s ",student.getStudentName());
            System.out.printf("[상태:%s] ",student.getStatus());
            System.out.print("[수강 과목 (필):");
            for (Subject subject : Util.listStudentSubjectByType(student, "MANDATORY")) {
                System.out.printf("%s,",subject.getSubjectName());
            }
            System.out.print("(선):");
            for (Subject subject : Util.listStudentSubjectByType(student, "CHOICE")) {
                System.out.printf("%s,",subject.getSubjectName());
            }
            System.out.print("] \n");
        }
        System.out.println("\n수강생 목록 조회 성공!");
    }

    //상태별 수강생 목록 조회
    public static void inquireStudentByStatus() {
        System.out.println("\n 상태별 수강생 목록을 조회합니다...");
        filterAndPrintStudentsByStatus("Green");
        filterAndPrintStudentsByStatus("Yellow");
        filterAndPrintStudentsByStatus("Red");
        System.out.println("\n상태별 수강생 목록 조회 성공!");
    }
    //상태별로 필터링해주는 메서드
    public static void filterAndPrintStudentsByStatus (String status) {
        List<Student> filteredStudents = CampManagementApplication.getStudentStore().stream()
                .filter(student -> status.equals(student.getStatus()))
                .toList();

        System.out.println("\n상태 : " + status + " 인 학생들");
        for (Student student : filteredStudents) {
            System.out.println(student.getStudentId() + " " +  student.getStudentName());
        }
    }

    // 수강생 상태 수정
    public static void updateStudentStatus() {
        String studentId = Util.getStudentId(); // 관리할 수강생 고유 번호
        System.out.print("수정할 수강생의 상태를 입력: ");
        String newStatus = sc.next();
        for(Student student : studentStore) {
            if (student.getStudentId().equals(studentId)) student.setStudentStatus(newStatus);
        }
        System.out.println("수강생 상태 수정 성공!\n");
    }
}



