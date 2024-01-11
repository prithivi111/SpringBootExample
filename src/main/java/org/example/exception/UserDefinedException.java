package org.example.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UserDefinedException extends ResponseEntityExceptionHandler { //if we extend ResponseEntityHandler, it will have
                                                                            //many methods which we can use it for our purpose.
                                                                            //We can only use with UserDefinedException without extending also.
                                                                            //If we don't extend, then we won't be able to access predefined
                                                                            //methods, which I have used in the body as well.
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<String> studentIsNotCorrectMethod(StudentNotFoundException e){  //This 'e' is just an instance variable. That has
                                                                                          // 601, and "The Given StudentID Is Not Correct"
                                                                                          // inside it.
     //  return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<String>("Error Code: " + e.getErrorCode() + "\n" +
                "Error Message: "  + e.getErrorMessage()+ "\n" +  "Please check the given id.", HttpStatus.BAD_REQUEST);
    }

//    @Override
//    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        return super.handleHttpRequestMethodNotSupported(ex, headers, status, request);
//    }

    //The above- and-down method is inside the ResponseEntityExceptionHandler which is overridden, and there are many inbuilt-methods like that.
    //This method will be invoked when a request is made with an unsupported HTTP method
    // (e.g., a GET request for an endpoint that only supports POST),or say, for mismatching put, get, post and delete that we normally do.
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<Object>("Please change http method type: ", HttpStatus.BAD_REQUEST);
    }
    //Inside the body, it had something written like... [return super.handleHttpRequestMethodNotSupported(ex, headers, status, request)];
    //which I changed into User understandable form..


    //This will invoke if the request is made with improper selection of media type like text, and providing JSON data, etc.
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        //return super.handleHttpMediaTypeNotSupported(ex, headers, status, request);
        return new ResponseEntity<Object>("Please select the appropriate Media Type, like JSON, text, etc.. ", HttpStatus.BAD_REQUEST);
    }


}
