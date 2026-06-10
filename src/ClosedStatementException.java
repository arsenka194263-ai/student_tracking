// Исключение при попытке редактировать закрытую ведомость
class ClosedStatementException extends IllegalStateException {
    public ClosedStatementException(String message) { super(message); }
}