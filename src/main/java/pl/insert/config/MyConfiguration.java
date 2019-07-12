package pl.insert.config;

import pl.insert.dao.EmployeeDao;
import pl.insert.dao.EmployeeDaoImpl;
import pl.insert.services.EmployeeService;
import pl.insert.services.EmployeeServiceImpl;
import pl.insert.spring.annotations.Bean;
import pl.insert.spring.dynamicproxypattern.EntityManagerProxy;

import javax.persistence.EntityManager;

public class MyConfiguration {

    public MyConfiguration() {
    }

    @Bean(name = "employeeService")
    public EmployeeService employeeService() {
        return new EmployeeServiceImpl();
    }

    @Bean(name = "employeeDao")
    public EmployeeDao employeeDao() {
        return new EmployeeDaoImpl();
    }

    @Bean(name = "entityManager")
    public EntityManager entityManager() {
        return new EntityManagerProxy();
    }
}