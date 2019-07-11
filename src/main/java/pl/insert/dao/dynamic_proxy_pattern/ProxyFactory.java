package pl.insert.dao.dynamic_proxy_pattern;

import pl.insert.dao.EmployeesDao;

import java.lang.reflect.Proxy;

public class ProxyFactory {

    public static Object newInstance(Object obj) {
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                new Class<?>[]{EmployeesDao.class}, new DynamicInvocationHandler(obj));
    }
}
