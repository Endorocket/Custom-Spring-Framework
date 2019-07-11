package pl.insert.config;

import pl.insert.dao.EmployeesDao;
import pl.insert.dao.EmployeesDaoImpl;
import pl.insert.services.EmployeeService;
import pl.insert.services.EmployeeServiceImpl;
import pl.insert.spring.annotations.Bean;

public class MyConfiguration {

    public MyConfiguration() {
    }

    @Bean(name = "employeeService")
    public EmployeeService employeeService() {

        System.out.println("MyConfiguration: cosTamcosTam()");

        return new EmployeeServiceImpl();
    }

    @Bean(name="employeesDao")
    public EmployeesDao employeesDao() {

        return new EmployeesDaoImpl();
    }
}
