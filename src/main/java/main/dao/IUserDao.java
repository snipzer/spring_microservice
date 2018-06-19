package main.dao;

import main.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface IUserDao extends CrudRepository<User, Long> {

    List<User> findByLastname(String lastname);

    User getByEmail(String email);
}
