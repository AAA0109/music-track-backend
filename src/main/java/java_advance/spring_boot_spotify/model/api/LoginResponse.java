package java_advance.spring_boot_spotify.model.api;

import java_advance.spring_boot_spotify.model.ResponseStatus;

public class LoginResponse {
    private ResponseStatus.Status status;
    private String message;

    public void setStatus(ResponseStatus.Status status) {
        this.status = status;
    }
    public ResponseStatus.Status getStatus() {
        return status;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
