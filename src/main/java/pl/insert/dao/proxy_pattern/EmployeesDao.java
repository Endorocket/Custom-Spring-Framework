package pl.insert.dao.proxy_pattern;

import org.hibernate.Query;
import org.hibernate.Session;
import pl.insert.dao.execute_around_method_pattern.TransactionTemplate;
import pl.insert.model.Employee;

import java.util.Date;
import java.util.List;

public class EmployeesDao implements IEmployeesDao {

    private Session session;

    public void setSession(Session session) {
        this.session = session;
    }

    public List<Employee> getEmployeeList() {

        String queryStr = "select emp from Employee emp";

        Query query = session.createQuery(queryStr);
        return query.list();
    }

    @Override
    public Employee getEmployeeById(Long empId) {
        return null;
    }

    @Override
    public void insertEmployee(Employee emp) {

    }

    @Override
    public void deleteEmployee(Employee emp) {

    }

    public static void main(String a[]) {

        IEmployeesDao empDao = new ProxyEmployeesDao();

        Employee emp = new Employee();
        emp.setName("Babu");
        emp.setDepartment("Security");
        emp.setJoinedOn(new Date());
        emp.setSalary(5250L);
        empDao.insertEmployee(emp);

        System.out.println("---------------------------");

        List<Employee> empList = empDao.getEmployeeList();
        System.out.println("emp size: " + empList.size());
        empList.forEach(System.out::println);

        System.out.println("---------------------------");
    }
}