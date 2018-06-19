package main.service;


import main.dao.IUserDao;
import main.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends BaseService<IUserDao, User> {

    @Autowired
    public UserService(IUserDao IUserDao) {
        super(IUserDao);
    }

    public List<User> findByLastname(String lastName) {
        return this.getDao().findByLastname(lastName);
    }
}
