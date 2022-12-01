package com.blog.exception;

import com.blog.payloads.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<APIResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        String message = ex.getMessage();
        APIResponse apiResponse = new APIResponse(message,false);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String, String> resp = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String field = ((FieldError) error).getField(); // getting field name and ((FieldError) error): error is typecast to FieldError
            String message = error.getDefaultMessage(); // this for getting error message.
            resp.put(field,message);
        });
        return new ResponseEntity<>(resp,HttpStatus.NOT_FOUND);
    }

}
