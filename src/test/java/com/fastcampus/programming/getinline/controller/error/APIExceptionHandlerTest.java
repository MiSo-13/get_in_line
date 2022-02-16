package com.fastcampus.programming.getinline.controller.error;

import com.fastcampus.programming.getinline.constant.ErrorCode;
import com.fastcampus.programming.getinline.dto.APIErrorResponse;
import com.fastcampus.programming.getinline.exception.GeneralException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.handler.DispatcherServletWebRequest;

import javax.validation.ConstraintViolationException;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class APIExceptionHandlerTest {

    private APIExceptionHandler sut;
    private WebRequest webRequest;

    @BeforeEach
    void setup() {
        sut = new APIExceptionHandler();
        webRequest = new DispatcherServletWebRequest(new MockHttpServletRequest());
    }

    @DisplayName("검증 오료 - 응답 데이터 정리")
    @Test
    void giveValidationException_whenCallingValidation_thenReturnsResponseEntity() {
        // give
        ConstraintViolationException e = new ConstraintViolationException(Set.of());
        // when
        ResponseEntity<Object> response = sut.validation(e, webRequest);
        // then
        assertThat(response)
                .hasFieldOrPropertyWithValue("body", APIErrorResponse.of(
                        false,
                        ErrorCode.VALIDATION_ERROR,
                        e
                ))
                .hasFieldOrPropertyWithValue("headers", HttpHeaders.EMPTY)
                .hasFieldOrPropertyWithValue("statusCode", HttpStatus.BAD_REQUEST);
    }

    @DisplayName("프로젝트 일반 오료 - 응답 데이터 정리")
    @Test
    void giveGeneralException_whenCallingValidation_thenReturnsResponseEntity() {
        // give
        ErrorCode errorCode = ErrorCode.INTERNAL_ERROR;
        GeneralException e = new GeneralException(errorCode);
        // when
        ResponseEntity<Object> response = sut.general(e, webRequest);
        // then
        assertThat(response)
                .hasFieldOrPropertyWithValue("body", APIErrorResponse.of(
                        false,
                        errorCode,
                        e
                ))
                .hasFieldOrPropertyWithValue("headers", HttpHeaders.EMPTY)
                .hasFieldOrPropertyWithValue("statusCode", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DisplayName("기타(전체) 오료 - 응답 데이터 정리")
    @Test
    void giveOtherException_whenCallingValidation_thenReturnsResponseEntity() {
        // give
        Exception e = new Exception();
        // when
        ResponseEntity<Object> response = sut.exception(e, webRequest);
        // then
        assertThat(response)
                .hasFieldOrPropertyWithValue("body", APIErrorResponse.of(
                        false,
                        ErrorCode.INTERNAL_ERROR,
                        e
                ))
                .hasFieldOrPropertyWithValue("headers", HttpHeaders.EMPTY)
                .hasFieldOrPropertyWithValue("statusCode", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}