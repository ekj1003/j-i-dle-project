package camp;

import camp.model.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestDb {
    public static void Dbadd() {
        Student plz = new Student("ST1","페라자");
        plz.setStudentStatus("Green");
        CampManagementApplication.studentStore.add(plz);
        plz.setSubjectList(new ArrayList<>(Arrays.asList("SU1","SU2","SU3","SU8","SU9")));

        Student kih = new Student("ST2","김인환");
        kih.setStudentStatus("Yellow");
        CampManagementApplication.studentStore.add(kih);
        kih.setSubjectList(new ArrayList<>(Arrays.asList("SU1","SU2","SU3","SU8","SU9")));

        Student kty = new Student("ST3","김태연");
        kty.setStudentStatus("Yellow");
        CampManagementApplication.studentStore.add(kty);
        kty.setSubjectList(new ArrayList<>(Arrays.asList("SU1","SU2","SU3","SU8","SU9")));

        Student nsh = new Student("ST4","노시환");
        nsh.setStudentStatus("Red");
        CampManagementApplication.studentStore.add(nsh);
        nsh.setSubjectList(new ArrayList<>(Arrays.asList("SU4","SU5","SU3","SU6","SU7")));

        Student ces = new Student("ST5","채은성");
        ces.setStudentStatus("Red");
        CampManagementApplication.studentStore.add(ces);
        ces.setSubjectList(new ArrayList<>(Arrays.asList("SU4","SU5","SU3","SU8","SU9")));

        Student ach = new Student("ST6","안치홍");
        ach.setStudentStatus("Green");
        CampManagementApplication.studentStore.add(ach);
        ach.setSubjectList(new ArrayList<>(Arrays.asList("SU1","SU2","SU3","SU8","SU9")));







    }
}
