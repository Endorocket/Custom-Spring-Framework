package pl.insert.old.dogs_for_testing;

import pl.insert.dao.dynamic_proxy_pattern.DynamicInvocationHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class DogDynamicInvocationHandler implements InvocationHandler {

    private static Logger logger = Logger.getLogger(DynamicInvocationHandler.class.getName());

    private final Map<String, Method> methods = new HashMap<>();

    private Object target;

    public DogDynamicInvocationHandler(Object target) {
        this.target = target;

        for (Method method : target.getClass().getDeclaredMethods()) {
            this.methods.put(method.getName(), method);
        }

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        logger.info("Before invoking: " + method.getName());

        Object result = methods.get(method.getName()).invoke(target, args);

        logger.info("Invoked method: " + method.getName());

        return result;
    }
}
