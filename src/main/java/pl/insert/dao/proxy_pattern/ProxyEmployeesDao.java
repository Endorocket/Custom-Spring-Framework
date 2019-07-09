package pl.insert.dao.proxy_pattern;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pl.insert.hibernate.HibernateUtil;
import pl.insert.model.Employee;

import java.util.List;

public class ProxyEmployeesDao implements IEmployeesDao {

    private EmployeesDao employeesDao;

    public ProxyEmployeesDao() {
        employeesDao = new EmployeesDao();
    }

    @Override
    public List<Employee> getEmployeeList() {

        List<Employee> employeeList = null;
        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();
//
//            employeesDao.setSession(session);
//
            employeeList = employeesDao.getEmployeeList();

        } catch (Exception ex) {
            ex.printStackTrace();
            // handle exception here
            if (transaction != null) transaction.rollback();
        } finally {
            try {
                if (session != null) session.close();
            } catch (Exception ex) {
            }
        }

        return employeeList;
    }

    @Override
    public Employee getEmployeeById(Long empId) {

        Employee employeeById = employeesDao.getEmployeeById(empId);

        return employeeById;
    }

    @Override
    public void insertEmployee(Employee emp) {

        employeesDao.insertEmployee(emp);

    }

    @Override
    public void deleteEmployee(Employee emp) {

        employeesDao.deleteEmployee(emp);

    }
}
