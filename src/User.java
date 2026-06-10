// User.java
abstract class User implements IAuthorizable {
    protected int id;
    protected String fullName;
    protected String login;

    public User(int id, String fullName, String login) {
        this.id = id;
        this.fullName = fullName;
        this.login = login;
    }

    @Override
    public boolean authorize(String inputLogin) throws UnauthorizedAccessException {
        if (inputLogin == null || inputLogin.trim().isEmpty()) {
            throw new UnauthorizedAccessException("Ошибка авторизации: пустое поле.");
        }
        // Безопасная ошибка без раскрытия личных данных
        if (!this.login.equals(inputLogin.trim())) {
            throw new UnauthorizedAccessException("Ошибка авторизации. Неверный логин.");
        }
        return true;
    }
}