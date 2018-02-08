package pl.kacperb333.java.foodbook.domain.commonexception;

public class EntityAlreadyExistsException extends Exception {
    public EntityAlreadyExistsException(String message) {
        super(message);
    }
}
