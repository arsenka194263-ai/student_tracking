import java.util.ArrayList;
import java.util.List;

public class Lesson {
    private int lessonId;
    private String date;
    private String theme;
    private Subject subject;
    private List<Attendance> visitors = new ArrayList<>();

    public Lesson(int id, String date, String theme, Subject subject) {
        this.lessonId = id;
        this.date = date;
        this.theme = theme;
        this.subject = subject;
    }

    public void registerAttendance(Attendance a) { visitors.add(a); }
}