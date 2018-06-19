package main.dto;

import main.entity.User;
import main.core.exception.EntityNotFoundException;
import main.util.ErrorUtil;

import java.io.Serializable;
import java.util.Date;

public class UserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private User user;


    private UserDto() {
    }

    public UserDto(User user) {
        if (user == null) {
            throw new EntityNotFoundException(ErrorUtil.ENTITY_NOT_FOUND);
        }
        this.user = user;
    }

    public Long getId() {
        return this.user.getId();
    }

    public String getFirstname() {
        return this.user.getFirstname();
    }

    public String getLastname() {
        return this.user.getLastname();
    }

    public String getEmail() {
        return this.user.getEmail();
    }

    public Date getBirthDate() {
        return this.user.getBirthdate();
    }

    public Long getRole() {
        return this.user.getRole();
    }

}
