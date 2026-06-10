import java.util.ArrayList;
import java.util.List;

public class Statement {
    private int statementId;
    private String creationDate;
    private boolean isClosed = false;
    private Subject subject;
    private List<Grade> gradeList = new ArrayList<>();

    public Statement(int id, Subject subject) {
        this.statementId = id;
        this.subject = subject;
    }

    public void addGrade(Grade g) { gradeList.add(g); }
    public void closeStatement() { this.isClosed = true; }
    public boolean getIsClosed() { return isClosed; }
}