package pl.insert.dao.proxy_pattern;

import pl.insert.model.Employee;

import java.util.List;

public interface IEmployeesDao {

    List<Employee> getEmployeeList();

    Employee getEmployeeById(Long empId);

    void insertEmployee(Employee emp);

    void deleteEmployee(Employee emp);
}
