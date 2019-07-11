package pl.insert.spring.dynamic_proxy_pattern;

import java.lang.reflect.Proxy;

public class BeanFactory {

    @SuppressWarnings("unchecked")
    public static <R> R newInstance(Object obj, Class<R> clazz) {
        return (R) Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                new Class<?>[]{clazz}, new DynamicInvocationHandler(obj));
    }
}
