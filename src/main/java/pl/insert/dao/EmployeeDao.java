package pl.insert.dao;

import pl.insert.model.Employee;

import java.util.List;

public interface EmployeeDao {

    List<Employee> getEmployeeList();

    Employee getEmployeeById(Long empId);

    void insertEmployee(Employee emp);

    void deleteEmployee(Long empId);
}
