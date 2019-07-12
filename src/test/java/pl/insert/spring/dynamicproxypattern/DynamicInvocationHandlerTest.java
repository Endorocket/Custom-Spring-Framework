package pl.insert.spring.dynamicproxypattern;

import org.junit.Before;
import org.junit.Test;
import pl.insert.config.MyConfiguration;
import pl.insert.dao.EmployeeDao;
import pl.insert.model.Employee;
import pl.insert.spring.context.ApplicationContext;

import java.util.Date;
import java.util.List;

public class DynamicInvocationHandlerTest {

    private EmployeeDao employeeDao;

    @Before
    public void setUp() {
        ApplicationContext context = new ApplicationContext(MyConfiguration.class);

        employeeDao = context.getBean("employeeDao", EmployeeDao.class);
    }

    @Test
    public void testGetEmployeeList() {

        List<Employee> employeeList = employeeDao.getEmployeeList();

        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
    }

    @Test
    public void testGetEmployeeById() {

        Employee employee = employeeDao.getEmployeeById(1L);

        System.out.println(employee);
    }

    @Test
    public void testInsertEmployee() {

        Employee employee = new Employee();

        employee.setName("Mateusz");
        employee.setDepartment("HR");
        employee.setJoinedOn(new Date());
        employee.setSalary(6000L);

        employeeDao.insertEmployee(employee);
    }

    @Test
    public void testDeleteEmployee() {

        employeeDao.deleteEmployee(25L);
    }

    @Test
    public void testObjectMethods() {

        int i = employeeDao.hashCode();

        System.out.println(i);
    }

}