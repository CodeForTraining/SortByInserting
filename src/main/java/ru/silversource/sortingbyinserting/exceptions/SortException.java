package ru.silversource.sortingbyinserting.exceptions;

public class SortException extends Exception {

    ErrorCode errorCode;

    public SortException(ErrorCode errorCode){
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode(){
        return errorCode;
    }
}
