package pl.insert.config;

import org.junit.Before;
import org.junit.Test;
import pl.insert.dao.EmployeeDao;
import pl.insert.services.EmployeeService;
import pl.insert.spring.context.ApplicationContext;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

public class ApplicationContextTest {

    private ApplicationContext context;

    @Before
    public void setUp() throws Exception {
        context = new ApplicationContext(MyConfiguration.class);
    }

    @Test
    public void getEmployeeServiceBean() {

        EmployeeService actualEmployeeService = context.getBean("employeeService", EmployeeService.class);

        System.out.println(actualEmployeeService);

        assertNotNull(actualEmployeeService);
    }

    @Test
    public void getEmployeeDaoBean() {

        EmployeeDao employeeDao = context.getBean("employeeDao", EmployeeDao.class);

        System.out.println(employeeDao);

        assertNotNull(employeeDao);
    }
}