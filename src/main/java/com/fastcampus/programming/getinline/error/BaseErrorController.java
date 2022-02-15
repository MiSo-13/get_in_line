package com.fastcampus.programming.getinline.error;

import com.fastcampus.programming.getinline.constant.ErrorCode;
import com.fastcampus.programming.getinline.dto.APIErrorResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class BaseErrorController implements ErrorController {
    @RequestMapping(path = "/error", produces = MediaType.TEXT_HTML_VALUE)  // 기본 error 페이지를 우리가 만들겠다 > implements ErrorController
    // 이쪽에서는 뷰를 잡음
    public ModelAndView errorHtml(
            HttpServletResponse response
    ) {
        HttpStatus status = HttpStatus.valueOf(response.getStatus());
        ErrorCode errorCode = status.is4xxClientError()
                ? ErrorCode.BAD_REQUEST : ErrorCode.INTERNAL_ERROR;

        Map<String, Object> map = new HashMap<>();

        map.put("statusCode", status.value());
        map.put("errorCode", errorCode);
        map.put("message", errorCode.getMessage(status.getReasonPhrase()));

        return new ModelAndView("error", map, status);
    }

    @RequestMapping("/error")  // json body 를 잡음
    public ResponseEntity<APIErrorResponse> error(
            HttpServletResponse response
    ) {
        HttpStatus status = HttpStatus.valueOf(response.getStatus());
        ErrorCode errorCode = status.is4xxClientError()
                ? ErrorCode.BAD_REQUEST : ErrorCode.INTERNAL_ERROR;

//        Map<String, Object> map = new HashMap<>();
//
//        map.put("statusCode", status.value());
//        map.put("errorCode", errorCode);
//        map.put("message", errorCode.getMessage(status.getReasonPhrase()));

        return ResponseEntity
                .status(status)
                .body(APIErrorResponse.of(false, errorCode));
    }
}
