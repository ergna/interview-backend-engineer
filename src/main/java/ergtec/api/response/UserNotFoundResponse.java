package ergtec.api.response;

public class UserNotFoundResponse {
    public final int status;
    public final String error;

    public UserNotFoundResponse(int status, String error) {
        this.status = status;
        this.error = error;
    }
}
