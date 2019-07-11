package pl.insert.services;

import pl.insert.dao.EmployeesDao;
import pl.insert.spring.annotations.Autowired;
import pl.insert.spring.annotations.Qualifier;

public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    @Qualifier(name="employeesDao")
    private EmployeesDao employeesDao;

    @Override
    public String toString() {
        return "EmployeeServiceImpl{" +
                "userDao=" + employeesDao +
                '}';
    }
}
