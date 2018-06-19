package main.util;

import main.dto.UserDto;
import main.entity.User;

import java.util.ArrayList;
import java.util.List;

public class DtoUtil {

    public static List<UserDto> createUserListDto(List<User> userList) {
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : userList) {
            userDtoList.add(new UserDto(user));
        }
        return userDtoList;
    }
}
