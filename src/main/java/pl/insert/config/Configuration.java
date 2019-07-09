package pl.insert.config;

import pl.insert.annotations.Bean;
import pl.insert.dao.UserDao;
import pl.insert.services.Service;
import pl.insert.services.ServiceImpl;

public class Configuration {

    public Configuration() {
    }

    @Bean(name = "mojaNazwa")
    public Service cosTamcosTam() {

        System.out.println("Configuration: cosTamcosTam()");

        return new ServiceImpl();
    }

    @Bean(name="mojaInnaNazwa")
    public Service inneCosTamCosTam() {

        System.out.println("Configuration: inneCosTamCosTam()");

        return new ServiceImpl();
    }

    @Bean(name="myUserDao")
    public UserDao userDao() {

        return new UserDao();
    }
}

/*

    Aspect

 */