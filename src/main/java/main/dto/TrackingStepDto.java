package main.dto;

import main.core.exception.EntityNotFoundException;
import main.entity.TrackingStep;
import main.enumeration.Etat;
import main.util.ErrorUtil;

import java.io.Serializable;
import java.util.Date;

public class TrackingStepDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private TrackingStep trackingStep;


    private TrackingStepDto() {
    }

    public TrackingStepDto(TrackingStep trackingStep) {
        if (trackingStep == null) {
            throw new EntityNotFoundException(ErrorUtil.ENTITY_NOT_FOUND);
        }
        this.trackingStep = trackingStep;
    }

    public Long getId() {
        return this.trackingStep.getId();
    }

    public String getLieu() {
        return this.trackingStep.getLieu();
    }

    public Date getDate() {
        return this.trackingStep.getDate();
    }

    public String getEtat() {
        return Etat.getEtatById(this.trackingStep.getEtat()).getLibelle();
    }
}