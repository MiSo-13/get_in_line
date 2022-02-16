package com.fastcampus.programming.getinline.controller.api;

import com.fastcampus.programming.getinline.constant.EventStatus;
import com.fastcampus.programming.getinline.dto.APIDataResponse;
import com.fastcampus.programming.getinline.dto.EventRequest;
import com.fastcampus.programming.getinline.dto.EventResponse;
import com.fastcampus.programming.getinline.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Validated
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class APIEventController {

    private final EventService eventService;

    @GetMapping("/events")
    public APIDataResponse<List<EventResponse>> getEvents(
            @Positive Long placeId,
            @Size(min = 2) String eventName,
            EventStatus eventStatus,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime eventStartDatetime,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime eventEndDatetime
    ) {
        List<EventResponse> responses = eventService.getEvents(
                placeId,
                eventName,
                eventStatus,
                eventStartDatetime,
                eventEndDatetime
        ).stream().map(EventResponse::from).collect(Collectors.toList());

        return APIDataResponse.of(responses);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/events")
    public APIDataResponse<String> createEvent(
            @Valid @RequestBody EventRequest eventRequest
    ) {
        log.debug("보고 싶은 값 : {}", eventRequest);
        boolean result = eventService.createEvent(eventRequest.toDTO());
        return APIDataResponse.of(Boolean.toString(result));
    }

    @GetMapping("/events/{eventId}")
    public APIDataResponse<EventResponse> getEvent(
            @PathVariable Long eventId
    ) {
        if (eventId.equals(2L)) {
            return APIDataResponse.empty();
        }
        return APIDataResponse.of(EventResponse.of(
                1L,
                "오후 운동",
                EventStatus.OPENED,
                LocalDateTime.of(2021, 1, 1, 13, 0, 0),
                LocalDateTime.of(2021, 1, 1, 16, 0, 0),
                0,
                24,
                "마스크 꼭 착용하세요"
        ));
    }

    @PutMapping("/events/{eventId}")
    public APIDataResponse<Void> modifyEvent(
            @PathVariable Long eventId,
            @RequestBody EventRequest eventRequest
    ) {
        return APIDataResponse.empty();
    }

    @DeleteMapping("/events/{eventId}")
    public APIDataResponse<Void> removeEvent(
            @PathVariable Long eventId
    ) {
        return APIDataResponse.empty();
    }
}
