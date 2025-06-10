package com.h2.Exception;

/**
 * Standard error response object for API exception handling.
 * Contains HTTP status, error message, and the request path.
 */
public class ErrorResponse {
    private int status;      // HTTP status code
    private String message;  // Error message
    private String path;     // Request path where the error occurred

    /**
     * Constructs an ErrorResponse with the given status, message, and path.
     * @param status HTTP status code
     * @param message Error message
     * @param path Request path
     */
    public ErrorResponse(int status, String message, String path) {
        this.status = status;
        this.message = message;
        this.path = path;
    }

    // Getters and Setters

    /**
     * Gets the HTTP status code.
     * @return status code
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the HTTP status code.
     * @param status status code
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Gets the error message.
     * @return error message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the error message.
     * @param message error message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the request path where the error occurred.
     * @return request path
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets the request path where the error occurred.
     * @param path request path
     */
    public void setPath(String path) {
        this.path = path;
    }
}
