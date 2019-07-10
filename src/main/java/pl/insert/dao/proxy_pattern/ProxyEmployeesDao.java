package pl.insert.dao.proxy_pattern;

import pl.insert.model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class ProxyEmployeesDao implements IEmployeesDao {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("pl.insert.example");

    private EmployeesDao employeesDao;

    public ProxyEmployeesDao() {
        employeesDao = new EmployeesDao();
    }

    @Override
    public List<Employee> getEmployeeList() {

        List<Employee> employeeList = getEntityManager().createQuery("select emp from Employee emp").getResultList();

        return employeeList;
    }

    @Override
    public Employee getEmployeeById(Long empId) {

        EntityManager entityManager = getEntityManager();
        Employee employee = getEntityManager().find(Employee.class, empId);
        entityManager.detach(employee);

        return employee;
    }

    @Override
    public void insertEmployee(Employee emp) {

        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        entityManager.persist(emp);
        transaction.commit();
    }

    @Override
    public void deleteEmployee(Long empId) {

        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Employee employee = entityManager.find(Employee.class, empId);
        entityManager.remove(employee);

        transaction.commit();
    }

    public  EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
