// Исключение при некорректном балле
class InvalidGradeException extends IllegalArgumentException {
    public InvalidGradeException(String message) { super(message); }
}