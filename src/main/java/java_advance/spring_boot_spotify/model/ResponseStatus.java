package java_advance.spring_boot_spotify.model;

public class ResponseStatus {
    public static enum Status {
        SUCCESS(200),
        ALREADY(201),
        NOT_FOUND(404),
        FAILED(900);

        private final int code;

        Status(int code) {this.code = code;}

        public int getCode() {return code;}
    }
}
