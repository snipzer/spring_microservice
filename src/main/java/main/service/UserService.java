package main.service;


import main.dao.IUserDao;
import main.entity.Tracking;
import main.enumeration.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends BaseService<IUserDao, Tracking> {
    public UserService(IUserDao IUserDao) {
        super(IUserDao);
    }

    public List<Tracking> findByLastname(String lastName) {
        Tracking tracking = new Tracking();
        Role.getRoleById(tracking.getRole());

        return this.getDao().findByLastname(lastName);
    }
}
