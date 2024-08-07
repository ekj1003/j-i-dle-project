package camp;

import camp.model.Store;
import camp.model.Student;
import camp.model.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StudentManagement {
    static Scanner sc = new Scanner(System.in);

    public static void createStudent() {
        System.out.println("\n수강생을 등록합니다...");
        System.out.print("수강생 이름 입력: ");
        String studentName = sc.nextLine();


        System.out.println("좋음 : Green");
        System.out.println("중간 : Yellow");
        System.out.println("나쁨 : Red");
        System.out.println("수강생의 상태를 입력해주세요 : ");
        String status = sc.nextLine();


        // 기능 구현 (필수 과목, 선택 과목)
        String mandatorysubject;
        String choicesubject;

        while (true) {
            System.out.println("필수과목을 입력해 주세요 : (, 띄어쓰기로 구분)");
            System.out.println("JAVA, 객체지향, Spring, JPA, MySQL");
            System.out.print("입력: ");

            mandatorysubject = sc.nextLine().trim();

            // 유효성 검사: 최소 과목 개수 확인
            List<String> subjectList = List.of(mandatorysubject.split(", "));
            if (subjectList.size() < 3) {
                System.out.println("입력된 과목이 충분하지 않습니다. 다시 입력해주세요.");
                continue;
            }
            List<String> mandatory = subjectList.stream().
                    filter(subjectName -> Store.subjectStore.stream().noneMatch(s -> s.getSubjectName().equalsIgnoreCase(subjectName)))
                    .collect(Collectors.toList());

            if (mandatory.isEmpty()) {
                break;
            } else {
                System.out.println("다음 과목을 찾을 수 없습니다: " + String.join(", ", mandatory));
                System.out.println("올바른 과목을 입력해주세요.");

            }
        }

        while (true) {

            System.out.println("선택과목을 입력해 주세요 : (, 띄어쓰기로 구분)");
            System.out.println("디자인 패턴, Spring Security, Redis, MongoDB");
            System.out.print("입력: ");

            choicesubject = sc.nextLine().trim();

            // 유효성 검사: 최소 과목 개수 확인
            List<String> subjectList = List.of(choicesubject.split(", "));
            if (subjectList.size() < 2) {
                System.out.println("입력된 과목이 충분하지 않습니다. 다시 입력해주세요.");
                continue;
            }


            // 과목 유효성 검사
            List<String> choice = subjectList.stream().filter(subjectName -> Store.subjectStore.stream().noneMatch(s -> s.getSubjectName().equalsIgnoreCase(subjectName))).collect(Collectors.toList());

            if (choice.isEmpty()) {
                break;
            } else {
                System.out.println("다음 과목을 찾을 수 없습니다: " + String.join(", ", choice));
                System.out.println("올바른 과목을 입력해주세요.");
            }
        }


        Student student = new Student(Store.sequence("INDEX_TYPE_STUDENT"), studentName); // 수강생 인스턴스 생성 예시 코드

        // 기능 구현
        List<String> subjectIds = new ArrayList<>();
        for (String subjectName : mandatorysubject.split(", ")) {
            Store.subjectStore.stream()
                    .filter(s -> s.getSubjectName().equalsIgnoreCase(subjectName))
                    .findFirst()
                    .ifPresent(subject -> subjectIds.add(subject.getSubjectId()));
        }

        for (String subjectName : choicesubject.split(", ")) {
            Store.subjectStore.stream()
                    .filter(s -> s.getSubjectName().equalsIgnoreCase(subjectName))
                    .findFirst()
                    .ifPresent(subject -> subjectIds.add(subject.getSubjectId()));
        }

        student.setStudentStatus(status);
        student.setSubjectList(subjectIds);


        Store.studentStore.add(student); // 학생 저장


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
        for (Student student : Store.getStudentStore()) {
            System.out.printf("%s ", student.getStudentId());
            System.out.printf("%s ", student.getStudentName());
            System.out.printf("[상태:%s] ", student.getStatus());
            System.out.print("[수강 과목 (필):");
            for (Subject subject : Util.listStudentSubjectByType(student, "MANDATORY")) {
                System.out.printf("%s,", subject.getSubjectName());
            }
            System.out.print("(선):");
            for (Subject subject : Util.listStudentSubjectByType(student, "CHOICE")) {
                System.out.printf("%s,", subject.getSubjectName());
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
    public static void filterAndPrintStudentsByStatus(String status) {
        List<Student> filteredStudents = Store.getStudentStore().stream().filter(student -> status.equals(student.getStatus())).toList();

        System.out.println("\n상태 : " + status + " 인 학생들");
        for (Student student : filteredStudents) {
            System.out.println(student.getStudentId() + " " + student.getStudentName());
        }
    }

    // 수강생 상태 수정
    public static void updateStudentStatus() {
        String studentId = Util.getStudentId(); // 관리할 수강생 고유 번호
        System.out.print("수정할 수강생의 상태를 입력: ");
        String newStatus = sc.next();
        for (Student student : Store.studentStore) {
            if (student.getStudentId().equals(studentId)) student.setStudentStatus(newStatus);
        }
        System.out.println("수강생 상태 수정 성공!\n");
    }

    public static void deleteStudent() {
        System.out.println("\n수강생을 삭제합니다...");

        String studentId = Util.getStudentId();

        // 학생 삭제
        boolean studentRemoved = Store.studentStore.removeIf(student -> student.getStudentId().equals(studentId));

        // 관련 점수 삭제
        Store.scoreStore.removeIf(score -> score.getStudentId().equals(studentId));

        if (studentRemoved) {
            System.out.println("수강생 삭제 완료");
        } else {
            System.out.println("해당 ID를 가진 수강생이 없습니다.");
        }
    }

}




