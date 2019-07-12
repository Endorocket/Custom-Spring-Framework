package pl.insert.spring.dynamicproxypattern;

import java.lang.reflect.Proxy;

public class BeanFactory {

    @SuppressWarnings("unchecked")
    public static <R> R newInstance(Object obj, Class<R> clazz) {
        return (R) Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                new Class<?>[]{clazz}, new DynamicInvocationHandler(obj));
    }
}
