package main.dao;

import main.entity.Tracking;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface IUserDao extends CrudRepository<Tracking, Long> {

    List<Tracking> findByLastname(String lastname);

    Tracking getByEmail(String email);
}
