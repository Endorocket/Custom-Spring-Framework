package pl.insert.dao.execute_around_method_pattern;

import javax.persistence.Query;
import pl.insert.model.Employee;

import java.util.Date;
import java.util.List;

public class EmployeesDao {

    private TransactionTemplate transactionTemplate = new TransactionTemplate();

    public List<Employee> getEmployeeList() {

        return transactionTemplate.execute((TransactionCallback<List<Employee>>) session -> {

            String queryStr = "select emp from Employee emp";
            Query query = session.createQuery(queryStr);
            return query.getResultList();
        });
    }

    public Employee getEmployeeById(Long empId) {

        return transactionTemplate.execute(session -> session.get(Employee.class, empId));
    }

    public void insertEmployee(Employee emp) {

        transactionTemplate.execute(session -> {
            session.save(emp);
            return null;
        });
    }

    public void deleteEmployee(Employee emp) {

        transactionTemplate.execute(session -> {
            session.delete(emp);
            return null;
        });
    }

    public static void main(String a[]) {

        EmployeesDao empDao = new EmployeesDao();

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

        Employee empObj = empDao.getEmployeeById(emp.getEmpId());
        System.out.println(empObj);

//        System.out.println("---------------------------");
//        empDao.deleteEmployee(empObj);

        System.out.println("---------------------------");

        empList = empDao.getEmployeeList();
        System.out.println("emp size: " + empList.size());
        empList.forEach(System.out::println);

        System.out.println("---------------------------");
    }
}