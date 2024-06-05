package com.example.jobhunter.service.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.jobhunter.model.RestResponse;

@RestControllerAdvice
public class GlobalException {
  @ExceptionHandler(IdInvalidException.class)
  public ResponseEntity<RestResponse<Object>> handleIdInvalidException(IdInvalidException e) {
    var res = new RestResponse<Object>();
    res.setStatusCode(HttpStatus.BAD_REQUEST.value());
    res.setError("IdInvalidException");
    res.setMessage(e.getMessage());

    return ResponseEntity.badRequest().body(res);
  }
}
