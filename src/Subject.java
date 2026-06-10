public class Subject {
    private int subjectId;
    private String title;
    private int totalHours;

    public Subject(int id, String title, int hours) {
        this.subjectId = id;
        this.title = title;
        this.totalHours = hours;
    }

    public String getSubjectInfo() { return title + " (" + totalHours + " ч.)"; }
    public String getTitle() { return title; }
}