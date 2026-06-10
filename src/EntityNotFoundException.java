// Исключение, если объект не найден в системе
class EntityNotFoundException extends Exception {
    public EntityNotFoundException(String message) { super(message); }
}