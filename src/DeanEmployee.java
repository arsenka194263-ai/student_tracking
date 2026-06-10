public class DeanEmployee extends User {
    private String department;

    public DeanEmployee(int id, String fullName, String login, String department) {
        super(id, fullName, login);
        this.department = department;
    }

    public Statement createStatement(int id, Subject subject) throws EntityNotFoundException {
        if (subject == null) {
            throw new EntityNotFoundException("Невозможно сформировать ведомость: дисциплина не указана.");
        }
        System.out.println("[Деканат] Ведомость по дисциплине \"" + subject.getTitle() + "\" успешно создана.");
        return new Statement(id, subject);
    }

    public void expelStudent(Student student) throws EntityNotFoundException {
        if (student == null) {
            throw new EntityNotFoundException("Студент не найден для процедуры отчисления.");
        }
        System.out.println("[Деканат] Внимание: студент " + student.fullName + " представлен к отчислению за долги.");
    }
}