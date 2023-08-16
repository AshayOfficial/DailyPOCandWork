package com.order.controller;

import com.order.beans.response.CommonResponseBean;
import com.order.exceptions.AlreadyExistsException;
import com.order.exceptions.NoDataFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<CommonResponseBean> handleNoDataFoundException(NoDataFoundException noDataFoundException) {

        CommonResponseBean noDataFound = CommonResponseBean.builder()
                .status(Boolean.FALSE)
                .message(noDataFoundException.getMessage())
                .build();
        log.error("ERROR :: {}", noDataFoundException.getMessage());
        return new ResponseEntity<>(noDataFound, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<CommonResponseBean> handleAlreadyExistsException(AlreadyExistsException alreadyExistsException) {

        CommonResponseBean alreadyExists = CommonResponseBean.builder()
                .status(Boolean.FALSE)
                .message(alreadyExistsException.getMessage())
                .build();
        log.error(alreadyExistsException.getMessage());
        return new ResponseEntity<>(alreadyExists, HttpStatus.CONFLICT);
    }
}
