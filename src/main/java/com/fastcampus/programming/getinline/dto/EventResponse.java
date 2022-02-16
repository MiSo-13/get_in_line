package com.fastcampus.programming.getinline.dto;

import com.fastcampus.programming.getinline.constant.EventStatus;
import lombok.Value;

import java.time.LocalDateTime;

@Value(staticConstructor = "of")  // java 14 버전부터 record 사용 가능
public class EventResponse {
    Long placeId;
    String eventName;
    EventStatus eventStatus;
    LocalDateTime eventStartDatetime;
    LocalDateTime eventEndDatetime;
    Integer currentNumberOfPeople;
    Integer capacity;
    String memo;

    public static EventResponse from(EventDto eventDto) {
        if (eventDto == null) {return null;}
        return EventResponse.of(
                eventDto.getPlaceId(),
                eventDto.getEventName(),
                eventDto.getEventStatus(),
                eventDto.getEventStartDatetime(),
                eventDto.getEventEndDatetime(),
                eventDto.getCurrentNumberOfPeople(),
                eventDto.getCapacity(),
                eventDto.getMemo()
        );
    }
}
