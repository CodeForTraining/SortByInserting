package ru.silversource.sortingbyinserting.exceptions;

public enum ErrorCode {
    ILLEGAL_ARGUMENT("Illegal argument"),
    MISSING_PREFIX("Missing prefix"),
    FILE_NOT_FOUND("File not found"),
    NOT_INTEGER("Not integer");
    private String errorString;

    ErrorCode(String errorString){
        this.errorString = errorString;
    }

    public String getErrorString(){
        return errorString;
    }
}
