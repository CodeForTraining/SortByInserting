package ru.silversource.sortingbyinserting.exceptions;

public enum ErrorCode {
    ILLEGAL_ARGUMENT("Illegal argument"),
    MISSING_PREFIX("Missing prefix");

    private String errorString;

    ErrorCode(String errorString){
        this.errorString = errorString;
    }

    public String getErrorString(){
        return errorString;
    }
}
