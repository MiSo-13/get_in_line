package com.fastcampus.programming.getinline.controller.error;

import com.fastcampus.programming.getinline.constant.ErrorCode;
import com.fastcampus.programming.getinline.dto.APIErrorResponse;
import com.fastcampus.programming.getinline.exception.GeneralException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import javax.validation.ConstraintViolationException;

@RestControllerAdvice(annotations = RestController.class)
// API 에 대한 ControllerAdvice 를 만듦
// (annotations = RestController.class) 를 이용해서
// RestController 를 사용하는 것만 감지 (api)
public class APIExceptionHandler  extends ResponseEntityExceptionHandler {
    // ResponseEntityExceptionHandler << spring 에서 정한 error 처리
    @ExceptionHandler  // GeneralException 발생에 경우
    public ResponseEntity<Object> validation(
            ConstraintViolationException e,
            WebRequest request
    ) {
        return getInternalResponseEntity(
                e,
                ErrorCode.VALIDATION_ERROR,
                HttpHeaders.EMPTY,
                HttpStatus.BAD_REQUEST,
                request
        );
    }

    @ExceptionHandler  // GeneralException 발생에 경우
    public ResponseEntity<Object> general(
            GeneralException e,
            WebRequest request
    ) {
        ErrorCode errorCode = e.getErrorCode();
        HttpStatus status = errorCode.isClientSideError()
                ? HttpStatus.BAD_REQUEST : HttpStatus.INTERNAL_SERVER_ERROR;

//        return ResponseEntity
//                .status(status)
//                .body(APIErrorResponse.of(
//                        false, errorCode, errorCode.getMessage(e)
//                ));
        return getInternalResponseEntity(e, errorCode, HttpHeaders.EMPTY, status, request);
    }

    @ExceptionHandler  // GeneralException 발생에 경우
    public ResponseEntity<Object> exception(
            Exception e,
            WebRequest request
    ) {
        return getInternalResponseEntity(e,
                ErrorCode.INTERNAL_ERROR,
                HttpHeaders.EMPTY,
                HttpStatus.INTERNAL_SERVER_ERROR,
                request
        );
    }

    @Override
    // body 가 전부 null 이기때문에 override 해서 새로 만들어 준다.
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex,
            Object body,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        ErrorCode errorCode = status.is4xxClientError()
                ? ErrorCode.SPRING_BAD_REQUEST : ErrorCode.SPRING_INTERNAL_ERROR;

        return getInternalResponseEntity(ex, errorCode, headers, status, request);
    }

    private ResponseEntity<Object> getInternalResponseEntity(
            Exception e,
            ErrorCode errorCode,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        return super.handleExceptionInternal(
                e,
                APIErrorResponse.of(
                        false,
                        errorCode.getCode(),
                        errorCode.getMessage(e)
                ),
                headers,
                status,
                request
        );
    }
}
