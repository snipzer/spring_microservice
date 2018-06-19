package main.service;

import main.dao.ITrackingStepDao;
import main.entity.TrackingStep;
import org.springframework.stereotype.Service;

@Service
public class TrackingStepService extends BaseService<ITrackingStepDao, TrackingStep> {

    TrackingStepService(ITrackingStepDao iTrackingStepDao) {
        super(iTrackingStepDao);
    }
}
