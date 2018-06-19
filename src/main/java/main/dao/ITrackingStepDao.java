package main.dao;

import main.entity.Tracking;
import main.entity.TrackingStep;
import org.springframework.data.repository.CrudRepository;


public interface ITrackingStepDao extends CrudRepository<TrackingStep, Long> {

}
