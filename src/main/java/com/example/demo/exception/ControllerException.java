package com.example.demo.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerException {
    @ExceptionHandler(CustomException.class)
    public String handleException(CustomException ex, Model model) {
        model.addAttribute("error", ex);
        return "error";
    }

}
