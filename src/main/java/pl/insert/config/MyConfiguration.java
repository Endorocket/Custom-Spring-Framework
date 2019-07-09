package pl.insert.config;

import pl.insert.annotations.Bean;
import pl.insert.dao.UserDao;
import pl.insert.services.Service;
import pl.insert.services.ServiceImpl;

public class MyConfiguration {

    public MyConfiguration() {
    }

    @Bean(name = "mojaNazwa")
    public Service cosTamcosTam() {

        System.out.println("MyConfiguration: cosTamcosTam()");

        return new ServiceImpl();
    }

    @Bean(name="mojaInnaNazwa")
    public Service inneCosTamCosTam() {

        System.out.println("MyConfiguration: inneCosTamCosTam()");

        return new ServiceImpl();
    }

    @Bean(name="myUserDao")
    public UserDao userDao() {

        return new UserDao();
    }
}
