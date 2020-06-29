package com.daeyun.exception;

public class ApiException extends RuntimeException{
    private static final long serialVersionUID = -3862288504721250334L;
    protected ErrorCode code;
    protected String customMessage = null;

    public static enum ErrorCode {
        OK(200, "OK"), InternalError(1, "Internal Error"),
        ParameterMissed(1000, "missed parameter"),
        NoResult(1040, "No result"),
        ;

        private final int id;
        private String message;

        ErrorCode(final int id, final String message) {
            this.id = id;
            this.message = message;
        }

        public int getId() {
            return id;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }

    public ApiException() {
    }

    public ApiException(String message) {
        this.customMessage = message;
    }

    public String getMessage() {
        if (this.customMessage != null) {
            return this.customMessage;
        }
        return this.code.getMessage();
    }

    public ErrorCode getErrorCode() {
        return code;
    }
}
