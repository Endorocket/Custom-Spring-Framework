package pl.insert.old.proxy_pattern;

import pl.insert.dao.EmployeesDao;
import pl.insert.dao.EmployeesDaoImpl;
import pl.insert.dao.dynamic_proxy_pattern.EntityManagerHolder;
import pl.insert.model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeesDaoProxy implements EmployeesDao {

    private EntityManagerFactory emf;
    private EmployeesDaoImpl employeesDaoImpl;

    public EmployeesDaoProxy() {
        emf = Persistence.createEntityManagerFactory("pl.insert.example");
        employeesDaoImpl = new EmployeesDaoImpl();
    }

    @Override
    public List<Employee> getEmployeeList() {

        EntityManager entityManager = getEntityManager();
        ThreadLocal<EntityManager> threadLocal = EntityManagerHolder.getThreadLocal();

        List<Employee> employeeList;

        try {
            threadLocal.set(entityManager);
            employeeList = employeesDaoImpl.getEmployeeList();

        } finally {
            threadLocal.remove();
        }

        return employeeList;
    }

    @Override
    public Employee getEmployeeById(Long empId) {

        EntityManager entityManager = getEntityManager();
        ThreadLocal<EntityManager> threadLocal = EntityManagerHolder.getThreadLocal();

        Employee employee;

        try {
            threadLocal.set(entityManager);
            employee = employeesDaoImpl.getEmployeeById(empId);

        } finally {
            threadLocal.remove();
        }

        entityManager.detach(employee);

        return employee;
    }

    @Override
    public void insertEmployee(Employee emp) {

        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        ThreadLocal<EntityManager> threadLocal = EntityManagerHolder.getThreadLocal();

        transaction.begin();

        try {
            threadLocal.set(entityManager);
            employeesDaoImpl.insertEmployee(emp);

        } finally {
            threadLocal.remove();
        }

        transaction.commit();
    }

    @Override
    public void deleteEmployee(Long empId) {

        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        ThreadLocal<EntityManager> threadLocal = EntityManagerHolder.getThreadLocal();

        transaction.begin();

        try {
            threadLocal.set(entityManager);
            employeesDaoImpl.deleteEmployee(empId);

        } finally {
            threadLocal.remove();
        }

        transaction.commit();
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
