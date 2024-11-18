package com.sneha.productservice.controllerAdvice;


import com.sneha.productservice.dtos.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ExceptionDto> handleArithmeticException(){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("ArithmeticException has happened");
        exceptionDto.setSolution("please try again");

        ResponseEntity<ExceptionDto> response = new ResponseEntity<>(
                exceptionDto,
                HttpStatus.BAD_REQUEST
        );

        return response;
        /** without using DTO class for response structure **/
//        ResponseEntity<String>  response = new ResponseEntity<>(
//                "Arithmetic Exception has happened",
//                HttpStatus.NOT_FOUND
//        );
//        return response;
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<String> handleArrayIndexOutOfBoundsException(){
        ResponseEntity<String>  response = new ResponseEntity<>(
                "ArrayIndexOutOfBound Exception has happened",
                HttpStatus.NOT_FOUND
        );
        return response;
    }

}
