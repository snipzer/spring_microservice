package main.dao;

import main.entity.Tracking;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ITrackingDao extends CrudRepository<Tracking, Long> {

}
