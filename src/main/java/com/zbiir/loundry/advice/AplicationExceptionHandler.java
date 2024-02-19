package com.zbiir.loundry.advice;

import com.zbiir.loundry.exception.IdCustomerOutOfBoudException;
import com.zbiir.loundry.exception.IdServedUnitOutOfBoundException;
import com.zbiir.loundry.exception.OrderExistException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handlerInvalidArgument(MethodArgumentNotValidException ex){
        Map<String,String> errorMap = new HashMap<String,String>();
            ex.getBindingResult().getAllErrors().forEach( (err) -> {
                String fieldName = ((FieldError) err).getField();
                String fieldMesage = err.getDefaultMessage();
                errorMap.put(fieldName,fieldMesage);
        });

        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IdCustomerOutOfBoudException.class)
    public Map<String,String> handlerIdCustomerOutOfBoudException(IdCustomerOutOfBoudException ex){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("message",ex.getMessage());
        return errorMap;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IdServedUnitOutOfBoundException.class)
    public Map<String,String> handlerIdServedUnitOutOfBoundException(IdServedUnitOutOfBoundException ex){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("message",ex.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(OrderExistException.class)
    public Map<String,String> handlerOrderExistException(OrderExistException ex){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("message",ex.getMessage());
        return errorMap;
    }



}
