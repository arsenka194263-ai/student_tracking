public class Teacher extends User {
    private String position;
    private String degree;

    public Teacher(int id, String fullName, String login, String position, String degree) {
        super(id, fullName, login);
        this.position = position;
        this.degree = degree;
    }

    public void setGrade(Student student, Subject subject, int value, Statement statement)
            throws ClosedStatementException, InvalidGradeException, EntityNotFoundException {

        if (student == null) throw new EntityNotFoundException("Ошибка: студент не зарегистрирован в системе.");
        if (subject == null) throw new EntityNotFoundException("Ошибка: дисциплина не найдена.");
        if (statement == null) throw new EntityNotFoundException("Ошибка: ведомость еще не создана деканатом.");

        if (statement.getIsClosed()) {
            throw new ClosedStatementException("Ошибка доступа: данная ведомость уже закрыта и сдана в архив!");
        }
        if (value < 2 || value > 5) {
            throw new InvalidGradeException("Некорректная оценка: " + value + ". Допускаются только баллы от 2 до 5.");
        }

        Grade newGrade = new Grade(value, "Экзамен", student, subject);
        student.addGrade(newGrade);
        statement.addGrade(newGrade);
        System.out.println("[Преподаватель] " + fullName + " выставил оценку " + value + " студенту " + student.fullName);
    }

    public void markAttendance(Student student, Lesson lesson, boolean present) throws EntityNotFoundException {
        if (student == null) throw new EntityNotFoundException("Студент не найден!");
        if (lesson == null) throw new EntityNotFoundException("Занятие не найдено!");

        Attendance a = new Attendance(present, student, lesson);
        student.addAttendance(a);
        lesson.registerAttendance(a);
        System.out.println("[Преподаватель] " + fullName + " отметил " + (present ? "ЯВКУ" : "Н/Я") + " студенту " + student.fullName);
    }
}