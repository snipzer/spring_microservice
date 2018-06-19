package main.util;

import main.dto.TrackingDto;
import main.entity.Tracking;

import java.util.ArrayList;
import java.util.List;

public class DtoUtil {

    public static List<TrackingDto> createTrackingListDto(List<Tracking> trackingList) {
        List<TrackingDto> userDtoList = new ArrayList<>();
        for (Tracking tracking : trackingList) {
            userDtoList.add(new TrackingDto(tracking));
        }
        return userDtoList;
    }
}
