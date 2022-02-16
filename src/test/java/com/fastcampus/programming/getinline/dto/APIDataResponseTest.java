package com.fastcampus.programming.getinline.dto;

import com.fastcampus.programming.getinline.constant.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class APIDataResponseTest {
    @DisplayName("문자열 데이터가 주어지면, 표준 성공 응답을 생성한다.")
    @Test
    void givenStringData_whenCreatingResponse_thenReturnSuccessfulResponse() {
        // Given
        String data = "test data";
        // When
        /*
        APIDataResponse response = APIDataResponse.of(
                true, ErrorCode.OK.getCode(), ErrorCode.OK.getMessage(), data
        );
        // 리팩토링
         */

        APIDataResponse<String> response = APIDataResponse.of(data);

        // Then
        assertThat(response)
//                .isNotNull()  뒤 검사에서 null 이 검사됨
                .hasFieldOrPropertyWithValue("success", true)
                .hasFieldOrPropertyWithValue("errorCode", ErrorCode.OK.getCode())
                .hasFieldOrPropertyWithValue("message", ErrorCode.OK.getMessage())
                .hasFieldOrPropertyWithValue("data", data);
    }

    @DisplayName("데이터가 없을 때, 비어있는 표준 성공 응답을 생성한다.")
    @Test
    void givenNothing_whenCreatingResponse_thenReturnEmptySuccessfulResponse() {
        // Given
        // When
        APIDataResponse<String> response = APIDataResponse.empty();

        // Then
        assertThat(response)
//                .isNotNull()  뒤 검사에서 null 이 검사됨
                .hasFieldOrPropertyWithValue("success", true)
                .hasFieldOrPropertyWithValue("errorCode", ErrorCode.OK.getCode())
                .hasFieldOrPropertyWithValue("message", ErrorCode.OK.getMessage())
                .hasFieldOrPropertyWithValue("data", null);
    }
}