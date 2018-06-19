package main.util;

import main.dto.UserDto;
import main.entity.Tracking;

import java.util.ArrayList;
import java.util.List;

public class DtoUtil {

    public static List<UserDto> createUserListDto(List<Tracking> trackingList) {
        List<UserDto> userDtoList = new ArrayList<>();
        for (Tracking tracking : trackingList) {
            userDtoList.add(new UserDto(tracking));
        }
        return userDtoList;
    }
}
