package main.service;

import main.dao.ITrackingDao;
import main.entity.Tracking;
import org.springframework.stereotype.Service;

@Service
public class TrackingService extends BaseService<ITrackingDao, Tracking> {

    TrackingService(ITrackingDao iTrackingDao) {
        super(iTrackingDao);
    }


}
