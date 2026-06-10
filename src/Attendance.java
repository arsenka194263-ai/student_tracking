public class Attendance {
    private boolean isPresent;
    private Student student;
    private Lesson lesson;

    public Attendance(boolean present, Student s, Lesson l) {
        this.isPresent = present;
        this.student = s;
        this.lesson = l;
    }
}