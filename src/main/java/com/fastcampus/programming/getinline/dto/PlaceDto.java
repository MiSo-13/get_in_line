package com.fastcampus.programming.getinline.dto;

import com.fastcampus.programming.getinline.constant.PlaceType;
import lombok.Value;


@Value(staticConstructor = "of")  // java 14 버전부터 record 사용 가능
public class PlaceDto {
    PlaceType placeType;
    String placeName;
    String address;
    String phoneNumber;
    Integer capacity;
    String memo;

//    private PlaceDto(
//            PlaceType placeType,
//            String placeName,
//            String address,
//            String phoneNumber,
//            Integer capacity,
//            String memo
//    ) {
//
//    }

//    public static PlaceDto of(
//            PlaceType placeType,
//            String placeName,
//            String address,
//            String phoneNumber,
//            Integer capacity,
//            String memo
//    ) {
//        return new PlaceDto(
//                placeType,
//                placeName,
//                address,
//                phoneNumber,
//                capacity,
//                memo
//        );
//    }
}
