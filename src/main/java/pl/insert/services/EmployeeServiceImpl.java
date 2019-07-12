package pl.insert.services;

import pl.insert.dao.EmployeeDao;
import pl.insert.spring.annotations.Autowired;
import pl.insert.spring.annotations.Qualifier;

public class EmployeeServiceImpl implements EmployeeService {



    @Autowired
    @Qualifier(name="employeeDao")
    private EmployeeDao employeeDao;

    @Override
    public String toString() {
        return "EmployeeServiceImpl{" +
                "userDao=" + employeeDao +
                '}';
    }
}
