package com.fastcampus.programming.getinline.dto;

import com.fastcampus.programming.getinline.constant.PlaceType;
import lombok.Value;

@Value(staticConstructor = "of")
public class PlaceRequest {
    PlaceType placeType;
    String placeName;
    String address;
    String phoneNumber;
    Integer capacity;
    String memo;
}
