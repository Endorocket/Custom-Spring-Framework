package pl.insert.dao;

import pl.insert.model.Employee;
import pl.insert.spring.annotations.PersistenceContext;
import pl.insert.spring.annotations.Qualifier;
import pl.insert.spring.annotations.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {

    @PersistenceContext
    @Qualifier(name = "entityManager")
    private EntityManager entityManager;

    public List<Employee> getEmployeeList() {

        return (List<Employee>) entityManager.createQuery("SELECT e FROM Employee e").getResultList();
    }

    @Override
    public Employee getEmployeeById(Long empId) {

        return entityManager.find(Employee.class, empId);
    }

    @Transactional
    @Override
    public void insertEmployee(Employee emp) {

        entityManager.persist(emp);
    }

    @Transactional
    @Override
    public void deleteEmployee(Long empId) {

        Employee employee = entityManager.find(Employee.class, empId);

        if (employee != null) {
            entityManager.remove(employee);
        }
    }

    @Override
    public String toString() {
        return "EmployeeDaoImpl{" +
                "entityManager=" + entityManager +
                '}';
    }
}
