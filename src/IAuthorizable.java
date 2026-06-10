interface IAuthorizable {
    boolean authorize(String login) throws UnauthorizedAccessException;
}