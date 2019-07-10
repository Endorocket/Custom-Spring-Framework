package pl.insert.dao.proxy_pattern;

import pl.insert.model.Employee;

import java.util.Date;
import java.util.List;

public class TestDao {

    public static void main(String[] args) {

        testDao();

    }

    public static void testDao() {

        IEmployeesDao empDao = new ProxyEmployeesDao();

//        Employee emp = new Employee();
//        emp.setName("Matthew");
//        emp.setDepartment("Pawelski");
//        emp.setJoinedOn(new Date());
//        emp.setSalary(5250L);
//        empDao.insertEmployee(emp);

        System.out.println("---------------------------");

        List<Employee> empList = empDao.getEmployeeList();
        System.out.println("emp size: " + empList.size());
        empList.forEach(System.out::println);
//
//        System.out.println("---------------------------");
//
//        Employee employee = empDao.getEmployeeById((long) 1);
//        System.out.println(employee);

//        empDao.deleteEmployee(7L);
    }


    public static void testThreadLocal() throws InterruptedException {

        ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
        threadLocal.remove();
        Thread thread1 = new Thread() {
            public void run() {
                try {
                    threadLocal.set(1);

                    System.out.println(threadLocal.get());
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {
                    threadLocal.remove();
                }
                System.out.println("Thread 1 umarł");
            }
        };
        Thread thread2 = new Thread() {
            public void run() {
                System.out.println("Thread 2:" + threadLocal.get());
                //threadLocal.set(2);
                //System.out.println(threadLocal.get());
                //threadLocal.remove();
            }
        };
        Thread thread3 = new Thread() {
            public void run() {
                threadLocal.set(3);
                System.out.println("Thread 3:" + threadLocal.get());
                //threadLocal.remove();
            }
        };
        thread1.start();
        Thread.sleep(500);
        thread2.start();
        Thread.sleep(500);
        thread3.start();
        System.out.println(threadLocal.get());
        threadLocal.remove();

    }
}

/*
    Następny proces:

    zautomatyzowanie proxy

    w refleksji: createProxy, podajemy interfejs i nam się robi proxy psa, pierwsze proxy tylko logger że coś jest robione

    Jeszcze następny krok:
    zautomatyzowanie dao przez refleksję

 */