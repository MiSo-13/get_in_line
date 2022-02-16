package com.fastcampus.programming.getinline.dto;

import com.fastcampus.programming.getinline.constant.EventStatus;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

@Value(staticConstructor = "of")
public class EventRequest {
    @NotNull @Positive Long placeId;
    @NotBlank String eventName;
    @NotNull EventStatus eventStatus;
    @NotNull LocalDateTime eventStartDatetime;
    @NotNull LocalDateTime eventEndDatetime;
    @NotNull @PositiveOrZero Integer currentNumberOfPeople;
    @NotNull @Positive Integer capacity;
    String memo;

    public EventDto toDTO() {
        return EventDto.of(
                this.getPlaceId(),
                this.getEventName(),
                this.getEventStatus(),
                this.getEventStartDatetime(),
                this.getEventEndDatetime(),
                this.getCurrentNumberOfPeople(),
                this.getCapacity(),
                this.getMemo(),
                null,
                null
        );
    }
}
