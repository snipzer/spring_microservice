package main.dto;

import main.entity.Tracking;
import main.core.exception.EntityNotFoundException;
import main.entity.TrackingStep;
import main.util.ErrorUtil;

import java.io.Serializable;
import java.util.List;

public class TrackingDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Tracking tracking;


    private TrackingDto() {
    }

    public TrackingDto(Tracking tracking) {
        if (tracking == null) {
            throw new EntityNotFoundException(ErrorUtil.ENTITY_NOT_FOUND);
        }
        this.tracking = tracking;
    }

    public Long getId() {
        return this.tracking.getId();
    }

    public String getNumber() {
        return this.tracking.getNumber();
    }

    public List<TrackingStep> getLastname() {
        return this.tracking.getTrackingSteps();
    }
}