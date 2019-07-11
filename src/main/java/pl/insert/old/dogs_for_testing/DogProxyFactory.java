package pl.insert.old.dogs_for_testing;

import java.lang.reflect.Proxy;

public class DogProxyFactory {

    public static Object newInstance(Object obj) {
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                new Class<?>[]{Dog.class}, new DogDynamicInvocationHandler(obj));
    }
}
