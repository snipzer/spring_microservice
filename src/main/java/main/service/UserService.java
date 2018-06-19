package main.service;


import main.dao.IUserDao;
import main.entity.User;
import main.enumeration.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends BaseService<IUserDao, User> {
    public UserService(IUserDao IUserDao) {
        super(IUserDao);
    }

    public List<User> findByLastname(String lastName) {
        User user = new User();
        Role.getRoleById(user.getRole());

        return this.getDao().findByLastname(lastName);
    }
}
