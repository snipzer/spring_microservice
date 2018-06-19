package main.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tracking")
public class Tracking {
    private Long id;

    private String number;

    private List<TrackingStep> trackingSteps;

    public Tracking() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "number")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @OneToMany(mappedBy = "trackingStep")
    @JoinColumn(name = "trackingStep", nullable = false)
    public List<TrackingStep> getTrackingSteps() {
        return trackingSteps;
    }

    public void setTrackingSteps(List<TrackingStep> trackingSteps) {
        this.trackingSteps = trackingSteps;
    }
}
