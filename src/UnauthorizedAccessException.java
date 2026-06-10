// Исключение при неверном логине
class UnauthorizedAccessException extends Exception {
    public UnauthorizedAccessException(String message) { super(message); }
}