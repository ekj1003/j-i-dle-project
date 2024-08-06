package camp;

import camp.model.Score;
import camp.model.Student;

import java.util.ArrayList;
import java.util.Arrays;

public class TestDb {
    public static void Dbadd() {
        Student plz = new Student("ST1","페라자");
        plz.setStudentStatus("Green");
        CampManagementApplication.studentStore.add(plz);
        plz.setSubjectList(new ArrayList<>(Arrays.asList("SU1","SU2","SU3","SU8","SU9")));
        Score plzSu1R1Score = new Score("ST1","SU1",1,88,"MANDATORY");
        CampManagementApplication.scoreStore.add(plzSu1R1Score);
        Score plzSu1R2Score = new Score("ST1","SU1",2,75,"MANDATORY");
        CampManagementApplication.scoreStore.add(plzSu1R2Score);
        Score plzSu1R3Score = new Score("ST1","SU1",3,77,"MANDATORY");
        CampManagementApplication.scoreStore.add(plzSu1R3Score);
        Score plzSu1R4Score = new Score("ST1","SU1",4,99,"MANDATORY");
        CampManagementApplication.scoreStore.add(plzSu1R4Score);
        Score plzSu1R5Score = new Score("ST1","SU1",5,65,"MANDATORY");
        CampManagementApplication.scoreStore.add(plzSu1R5Score);
        Score plzSu1R6Score = new Score("ST1","SU1",6,37,"MANDATORY");
        CampManagementApplication.scoreStore.add(plzSu1R6Score);

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
