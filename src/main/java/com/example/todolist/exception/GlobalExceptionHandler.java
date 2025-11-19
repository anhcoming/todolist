package com.example.todolist.exception;


import com.example.todolist.dto.ResponseDto;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;


@RestControllerAdvice
public class GlobalExceptionHandler {
    private final MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseDto<Void>> handleValidationError(RuntimeException ex) {
        Locale locale = LocaleContextHolder.getLocale();
        String message;
        if (ex.getMessage() != null && ex.getMessage().startsWith("error.")) {
            message = getLocalizedMessage(ex.getMessage(), locale);
        } else {
            message = ex.getMessage();
        }
        ResponseDto<Void> resp = new ResponseDto<>(HttpStatus.BAD_REQUEST, message, null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
    }


    public String getLocalizedMessage(String code, Locale locale) {
        try {
            return messageSource.getMessage(code, null, locale);
        } catch (NoSuchMessageException e) {
            return code; // Trả về code gốc nếu không tìm thấy mã lỗi
        }
    }


}
