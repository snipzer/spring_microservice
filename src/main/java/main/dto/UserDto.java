package main.dto;

import main.entity.Tracking;
import main.core.exception.EntityNotFoundException;
import main.util.ErrorUtil;

import java.io.Serializable;
import java.util.Date;

public class UserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Tracking tracking;


    private UserDto() {
    }

    public UserDto(Tracking tracking) {
        if (tracking == null) {
            throw new EntityNotFoundException(ErrorUtil.ENTITY_NOT_FOUND);
        }
        this.tracking = tracking;
    }

    public Long getId() {
        return this.tracking.getId();
    }

    public String getFirstname() {
        return this.tracking.getFirstname();
    }

    public String getLastname() {
        return this.tracking.getLastname();
    }

    public String getEmail() {
        return this.tracking.getEmail();
    }

    public Date getBirthDate() {
        return this.tracking.getBirthdate();
    }

    public Long getRole() {
        return this.tracking.getRole();
    }

}
