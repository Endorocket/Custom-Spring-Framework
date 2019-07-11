package pl.insert.config;

import org.junit.Before;
import org.junit.Test;
import pl.insert.services.Service;

import static org.junit.Assert.*;

public class ApplicationContextTest {

    private ApplicationContext context;

    @Before
    public void setUp() throws Exception {
        context = new ApplicationContext(MyConfiguration.class);
    }

    @Test
    public void getBeanTest() {

        Service actualService = context.getBean("mojaNazwa", Service.class);

        System.out.println(actualService);

        assertNotNull(actualService);
    }
}