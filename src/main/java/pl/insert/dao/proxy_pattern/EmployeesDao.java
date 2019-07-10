package pl.insert.dao.proxy_pattern;

import pl.insert.model.Employee;

import java.util.List;

public class EmployeesDao implements IEmployeesDao {

    public List<Employee> getEmployeeList() {

        String queryStr = "select emp from Employee emp";

//        System.out.println(HibernateUtil.getSessionFactory().getCurrentSession());

//        Query query = HibernateUtil.getSessionFactory().getCurrentSession().createQuery(queryStr);

//        return query.list();

        return null;
    }

    @Override
    public Employee getEmployeeById(Long empId) {
        return null;
    }

    @Override
    public void insertEmployee(Employee emp) {

    }

    @Override
    public void deleteEmployee(Long empId) {

    }

    public static void main(String a[]) {

        IEmployeesDao empDao = new ProxyEmployeesDao();

//        Employee emp = new Employee();
//        emp.setName("Matthew");
//        emp.setDepartment("Kovalsky");
//        emp.setJoinedOn(new Date());
//        emp.setSalary(5250L);
//        empDao.insertEmployee(emp);
//
//        System.out.println("---------------------------");

        List<Employee> empList = empDao.getEmployeeList();
        System.out.println("emp size: " + empList.size());
        empList.forEach(System.out::println);
//
//        System.out.println("---------------------------");
//
//        Employee employee = empDao.getEmployeeById((long) 1);
//        System.out.println(employee);

    }
}
/*

treetlocalstorage
pamięc lokalna wątku
entitymanager w proxy go dajemy tam a w real subject otrzymujemy go
w springu tego jest pełno
testy na wielu wątkach
zmienna statyczna get i jeszcze jedna metoda

w treedlocalstorage ważne by wyczyścić pamięc jak nie jest to potrzebne w finally threadlocal.remove()
 */