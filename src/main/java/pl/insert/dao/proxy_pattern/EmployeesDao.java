package pl.insert.dao.proxy_pattern;

import pl.insert.model.Employee;

import javax.persistence.EntityManager;
import java.util.List;

public class EmployeesDao implements IEmployeesDao {

    public List<Employee> getEmployeeList() {

        EntityManager entityManager = EntityManagerHolder.getEntityManager();

        //noinspection unchecked
        return (List<Employee>) entityManager.createQuery("SELECT e FROM Employee e").getResultList();
    }

    @Override
    public Employee getEmployeeById(Long empId) {

        EntityManager entityManager = EntityManagerHolder.getEntityManager();

        return entityManager.find(Employee.class, empId);
    }

    @Override
    public void insertEmployee(Employee emp) {

        EntityManager entityManager = EntityManagerHolder.getEntityManager();

        entityManager.persist(emp);
    }

    @Override
    public void deleteEmployee(Long empId) {

        EntityManager entityManager = EntityManagerHolder.getEntityManager();

        Employee employee = entityManager.find(Employee.class, empId);

        if (employee != null) {
            entityManager.remove(employee);
        }
    }
}
