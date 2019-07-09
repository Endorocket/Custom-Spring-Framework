package pl.insert.services;

import pl.insert.annotations.Autowired;
import pl.insert.annotations.Qualifier;
import pl.insert.dao.UserDao;

public class ServiceImpl implements Service {

    @Autowired
    @Qualifier(name="myUserDao")
    private UserDao userDao;

    @Override
    public String toString() {
        return "ServiceImpl{" +
                "userDao=" + userDao +
                '}';
    }
}
