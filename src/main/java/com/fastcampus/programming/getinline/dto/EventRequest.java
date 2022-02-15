package com.fastcampus.programming.getinline.dto;

import com.fastcampus.programming.getinline.constant.EventStatus;
import lombok.Value;

import java.time.LocalDateTime;

@Value(staticConstructor = "of")
public class EventRequest {
    Long placeId;
    String eventName;
    EventStatus eventStatus;
    LocalDateTime eventStartDatetime;
    LocalDateTime eventEndDatetime;
    Integer currentNumberOfPeople;
    Integer capacity;
    String memo;
}
