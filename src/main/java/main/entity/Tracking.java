package main.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tracking")
public class Tracking {
    private Long id;

    private String userId;

    private String productId;

    private String name;

    private List<TrackingStep> trackingSteps;

    public Tracking() {
        trackingSteps = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column
    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @OneToMany
    public List<TrackingStep> getTrackingSteps() {
        return trackingSteps;
    }

    public void setTrackingSteps(List<TrackingStep> trackingSteps) {
        this.trackingSteps = trackingSteps;
    }

    @Transient
    public TrackingStep retrieveLastStep() {
        return this.trackingSteps.get(this.trackingSteps.size() - 1);
    }

    @Transient
    public Boolean removeStepById(Long idStep) {
        int initialSize = trackingSteps.size();
        for(TrackingStep step : trackingSteps) {
            if(step.getId().equals(idStep)) {
                trackingSteps.remove(step);
            }
        }
        int finalSize = trackingSteps.size();
        return (finalSize < initialSize);
    }
}
