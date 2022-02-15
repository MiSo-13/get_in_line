package com.fastcampus.programming.getinline.error;

import com.fastcampus.programming.getinline.controller.error.BaseErrorController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BaseErrorController.class)
class BaseErrorControllerTest {
    private final MockMvc mvc;

    public BaseErrorControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[view][GET] 에러 페이지 - 페이지 없음")
    @Test
    void givenWrongURL_whenRequestingPage_thenReturn404ErrorPage(
    ) throws Exception{
        // Given

        // When & Then
        mvc.perform(get("/wrong-url")) // 잘못된 url 호출
                .andExpect(status().isNotFound())
                .andDo(print());
    }
}