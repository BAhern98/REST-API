/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;


import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//import org.springframework.dao.DataIntegrityViolationException;
/**
 *
 * @author Brendan
 */
//@ControllerAdvice
//class GlobalControllerExceptionHandler {
//    @ResponseStatus(HttpStatus.CONFLICT)  // 409
//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public void handleConflict() {
//        // Nothing to do
//    }
//}
//@Controller
//public class MyErrorController implements ErrorController  {
// 
//    @RequestMapping("/error")
//    public String handleError() {
//        //do something like logging
//        return "error";
//    }
// 
//    @Override
//    public String getErrorPath() {
//        return "/error";
//    }
//}
@ControllerAdvice
public class RestResponseEntityExceptionHandler   
  extends ResponseEntityExceptionHandler {
 
    @ExceptionHandler(value  = {Exception.class, IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<Object> handleConflict(
      RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "An error has occoured ";
        return handleExceptionInternal(ex, bodyOfResponse, 
          new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}