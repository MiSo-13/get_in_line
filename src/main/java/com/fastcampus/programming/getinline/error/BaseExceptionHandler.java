package com.fastcampus.programming.getinline.error;

import com.fastcampus.programming.getinline.constant.ErrorCode;
import com.fastcampus.programming.getinline.dto.APIErrorResponse;
import com.fastcampus.programming.getinline.exception.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
// 전체 컨트롤러 동작을 감시
// 뷰에 대한 ControllerAdvice 를 만듦
public class BaseExceptionHandler {
    @ExceptionHandler  // GeneralException 발생에 경우
    public ModelAndView general(
            GeneralException e
    ) {
        ErrorCode errorCode = e.getErrorCode();
        HttpStatus status = errorCode.isClientSideError()
                ? HttpStatus.BAD_REQUEST : HttpStatus.INTERNAL_SERVER_ERROR;

        Map<String, Object> map = new HashMap<>();

        map.put("statusCode", status.value());
        map.put("errorCode", errorCode);
        map.put("message", errorCode.getMessage(e));

        return new ModelAndView("error", map, status);
    }

    @ExceptionHandler  // GeneralException 발생에 경우
    public ModelAndView exception(
            Exception e
    ) {
        ErrorCode errorCode = ErrorCode.INTERNAL_ERROR;
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        Map<String, Object> map = new HashMap<>();

        map.put("statusCode", status.value());
        map.put("errorCode", errorCode);
        map.put("message", errorCode.getMessage(e));

        return new ModelAndView("error", map, status);
    }
}
