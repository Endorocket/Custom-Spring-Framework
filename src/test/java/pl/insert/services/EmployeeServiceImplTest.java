package pl.insert.services;

import org.junit.Before;
import org.junit.Test;
import pl.insert.config.MyConfiguration;
import pl.insert.model.Employee;
import pl.insert.spring.context.ApplicationContext;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class EmployeeServiceImplTest {

    private EmployeeService employeeService;

    @Before
    public void setUp() throws Exception {
        ApplicationContext context = new ApplicationContext(MyConfiguration.class);
        employeeService = context.getBean("employeeService", EmployeeService.class);
    }

    @Test
    public void getEmployeeList() {

        List<Employee> employeeList = employeeService.getEmployeeList();

        System.out.println(employeeList);
    }

    @Test
    public void getEmployeeById() {

        Employee employeeById = employeeService.getEmployeeById(1L);

        System.out.println(employeeById);
    }

    @Test
    public void insertEmployee() {

        Employee employee = new Employee();

        employee.setName("Mateusz");
        employee.setDepartment("HR");
        employee.setJoinedOn(new Date());
        employee.setSalary(6000L);

        employeeService.insertEmployee(employee);
    }

    @Test
    public void deleteEmployee() {

        employeeService.deleteEmployee(1L);
    }
}