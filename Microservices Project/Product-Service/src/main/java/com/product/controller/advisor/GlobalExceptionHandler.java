package com.product.controller.advisor;

import com.product.beans.response.CommonResponseBean;
import com.product.exceptions.AlreadyExistsException;
import com.product.exceptions.NoDataFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<CommonResponseBean> handleNoDataFoundException(NoDataFoundException noDataFoundException) {

        log.error("ERROR :: {}", noDataFoundException.getMessage());
        CommonResponseBean noDataFound = CommonResponseBean.builder()
                .status(Boolean.FALSE)
                .message(noDataFoundException.getMessage())
                .build();
        return new ResponseEntity<>(noDataFound, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<CommonResponseBean> handleDataAlreadyExistsException(AlreadyExistsException alreadyExistsException) {

        log.error("ERROR :: {}", alreadyExistsException.getMessage());
        CommonResponseBean alreadyExists = CommonResponseBean.builder()
                .status(Boolean.FALSE)
                .message(alreadyExistsException.getMessage())
                .build();
        return new ResponseEntity<>(alreadyExists, HttpStatus.CONFLICT);
    }
}
