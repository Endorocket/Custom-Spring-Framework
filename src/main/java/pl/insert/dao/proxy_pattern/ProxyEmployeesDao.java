package pl.insert.dao.proxy_pattern;

import pl.insert.model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class ProxyEmployeesDao implements IEmployeesDao {

    private EntityManagerFactory emf;
    private EmployeesDao employeesDao;

    public ProxyEmployeesDao() {
        emf = Persistence.createEntityManagerFactory("pl.insert.example");
        employeesDao = new EmployeesDao();
    }

    @Override
    public List<Employee> getEmployeeList() {

        EntityManager entityManager = getEntityManager();
        ThreadLocal<EntityManager> threadLocal = EntityManagerHolder.getThreadLocal();

        List<Employee> employeeList;

        try {
            threadLocal.set(entityManager);
            employeeList = employeesDao.getEmployeeList();

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
            employee = employeesDao.getEmployeeById(empId);

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
            employeesDao.insertEmployee(emp);

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
            employeesDao.deleteEmployee(empId);

        } finally {
            threadLocal.remove();
        }

        transaction.commit();
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
