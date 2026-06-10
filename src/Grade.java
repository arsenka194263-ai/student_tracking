public class Grade {
    private int gradeId;
    private int value;
    private String date;
    private String controlType;
    private Student student;
    private Subject subject;

    public Grade(int value, String type, Student s, Subject sub) {
        this.value = value;
        this.controlType = type;
        this.student = s;
        this.subject = sub;
    }

    public int getValue() { return value; }
    public String getSubjectTitle() { return subject.getTitle(); }
    public String getGradeInfo() {
        return subject.getTitle() + ": " + value + " (" + controlType + ")";
    }
}