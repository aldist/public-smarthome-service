package de.aldist.smarthomeappserver.dto;

public class ErrorResponse {

    private final String message;
    private final String path;

    public ErrorResponse(String message, String path) {
        this.message = message;
        this.path = path;
    }

    public String getMessage() {
        return this.message;
    }

    public String getPath() {
        return this.path;
    }
}
