package ergtec.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("No user found");
    }
}
