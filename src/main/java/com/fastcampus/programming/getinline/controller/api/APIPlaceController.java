package com.fastcampus.programming.getinline.controller.api;

import com.fastcampus.programming.getinline.constant.PlaceType;
import com.fastcampus.programming.getinline.dto.APIDataResponse;
import com.fastcampus.programming.getinline.dto.PlaceDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class APIPlaceController {

    @GetMapping("/places")
    public APIDataResponse<List<PlaceDto>> getPlaces() {
        return APIDataResponse.of(List.of(
                        PlaceDto.of(
                                PlaceType.COMMON,
                                "테스트",
                                "테스트시 테스트구",
                                "010-1234-5678",
                                30,
                                "신장개업"
                        )
                )
        );
    }

    @PostMapping("/places")
    public Boolean createPlace() {
        return true;
    }

    @GetMapping("/places/{placeId}")
    public APIDataResponse<PlaceDto> getPlace(
            @PathVariable Integer placeId
    ) {
        if (placeId.equals(2)) {  // 임시
            return APIDataResponse.of(null);
        }

        return APIDataResponse.of(
                PlaceDto.of(
                        PlaceType.COMMON,
                        "테스트",
                        "테스트시 테스트구",
                        "010-1234-5678",
                        30,
                        "신장개업"
                )

        );
    }

    @PutMapping("/places/{placeId}")
    public Boolean modifyPlace(
            @PathVariable Integer placeId
    ) {
        return true;
    }

    @DeleteMapping("/places/{placeId}")
    public Boolean removePlace(
            @PathVariable Integer placeId
    ) {
        return true;
    }
}
