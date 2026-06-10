import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    private String recordBookNum;
    private String group;
    private List<Grade> grades = new ArrayList<>();
    private List<Attendance> attendanceRecords = new ArrayList<>();

    public Student(int id, String fullName, String login, String recordBookNum, String group) {
        super(id, fullName, login);
        this.recordBookNum = recordBookNum;
        this.group = group;
    }

    public void getGrades() {
        System.out.println("\n--- Успеваемость студента: " + fullName + " ---");
        if (grades.isEmpty()) {
            System.out.println("Оценок в системе пока нет.");
            return;
        }
        for (Grade g : grades) {
            System.out.println(g.getGradeInfo());
        }
    }

    public void submitLab() {
        System.out.println("[Студент] " + fullName + " успешно отправил отчет по ЛР на сервер.");
    }

    public void addGrade(Grade g) {
        if (g == null) throw new IllegalArgumentException("Объект оценки не может быть null!");
        grades.add(g);
    }

    public void addAttendance(Attendance a) {
        if (a == null) throw new IllegalArgumentException("Объект посещаемости не может быть null!");
        attendanceRecords.add(a);
    }
}