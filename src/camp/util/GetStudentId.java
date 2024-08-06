package camp.util;

import java.util.Scanner;

public class GetStudentId {
    static Scanner sc = new Scanner(System.in);
    //수강생 ID 입력
    public static String getStudentId() {
        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        return sc.next();
    }
}
