package pl.insert.services;

import pl.insert.dao.EmployeeDao;
import pl.insert.model.Employee;
import pl.insert.spring.annotations.Autowired;
import pl.insert.spring.annotations.Qualifier;
import pl.insert.spring.annotations.Transactional;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    @Qualifier(name = "employeeDao")
    private EmployeeDao employeeDao;

    @Override
    public List<Employee> getEmployeeList() {
        return employeeDao.getEmployeeList();
    }

    @Override
    public Employee getEmployeeById(Long empId) {
        return employeeDao.getEmployeeById(empId);
    }

    @Transactional
    @Override
    public void insertEmployee(Employee emp) {
        employeeDao.insertEmployee(emp);
    }

    @Transactional
    @Override
    public void deleteEmployee(Long empId) {
        employeeDao.deleteEmployee(empId);
    }

    @Override
    public String toString() {
        return "EmployeeServiceImpl{" +
                "userDao=" + employeeDao +
                '}';
    }
}
