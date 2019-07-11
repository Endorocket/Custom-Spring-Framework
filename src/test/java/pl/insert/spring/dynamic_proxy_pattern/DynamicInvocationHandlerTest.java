package pl.insert.spring.dynamic_proxy_pattern;

import org.junit.Before;
import org.junit.Test;
import pl.insert.config.MyConfiguration;
import pl.insert.dao.EmployeesDao;
import pl.insert.dao.EmployeesDaoImpl;
import pl.insert.model.Employee;
import pl.insert.spring.context.ApplicationContext;

import java.util.Date;
import java.util.List;

public class DynamicInvocationHandlerTest {

    private EmployeesDao employeesDao;

    @Before
    public void setUp() {
        ApplicationContext context = new ApplicationContext(MyConfiguration.class);

        employeesDao = context.getBean("employeesDao", EmployeesDao.class);
    }

    @Test
    public void testGetEmployeeList() {

        List<Employee> employeeList = employeesDao.getEmployeeList();

        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
    }

    @Test
    public void testGetEmployeeById() {

        Employee employee = employeesDao.getEmployeeById(1L);

        System.out.println(employee);
    }

    @Test
    public void testInsertEmployee() {

        Employee employee = new Employee();

        employee.setName("Mateusz");
        employee.setDepartment("HR");
        employee.setJoinedOn(new Date());
        employee.setSalary(6000L);

        employeesDao.insertEmployee(employee);
    }

    @Test
    public void testDeleteEmployee() {

        employeesDao.deleteEmployee(23L);
    }

    @Test
    public void testObjectMethods() {

        int i = employeesDao.hashCode();

        System.out.println(i);
    }

}