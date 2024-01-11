package org.example.exception;

public class StudentNotFoundException extends RuntimeException {
    //This is just a method I have written and wanted to print this at UI.
    public String getMessage() {

        return "STUDENT NOT FOUND because you may have given the ID incorrectly.";
    }
    private String errorCode;
    private String errorMessage;

    //This method will be invoked!
    public StudentNotFoundException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public StudentNotFoundException(){
        super();
        // Even if we don't write super(), every method/constructor will have super() method, and it will call the super class's constructor
        //And the super class is RunTimeException here
        //Again the super class of RunTimeException is Exception.
        // Again the Exception has the super Class Object
        //So, the super() method of Class Object is also being invoked!
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}