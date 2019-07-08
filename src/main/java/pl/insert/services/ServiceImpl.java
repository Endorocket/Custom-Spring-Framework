package pl.insert.services;

import pl.insert.annotations.Autowired;
import pl.insert.annotations.Qualifier;
import pl.insert.dao.UserDao;

public class ServiceImpl implements Service {

    @Autowired
    @Qualifier(name="asdasd")
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
