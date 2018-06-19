package main.entity;

import javax.persistence.*;
import java.util.Date;

@Entity(name="tracking_step")
public class TrackingStep {

    private Long id;

    private Date date;

    private String lieu;

    private Long etat;

    private Tracking tracking;

    public TrackingStep() {
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
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column
    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    @Column
    public Long getEtat() {
        return etat;
    }

    public void setEtat(Long etat) {
        this.etat = etat;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tracking", nullable = false)
    public Tracking getTracking() {
        return tracking;
    }

    public void setTracking(Tracking tracking) {
        this.tracking = tracking;
    }
}
