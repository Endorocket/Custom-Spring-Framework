package pl.insert.spring.dynamic_proxy_pattern;

import pl.insert.spring.annotations.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class DynamicInvocationHandler implements InvocationHandler {

    private static Logger logger = Logger.getLogger(DynamicInvocationHandler.class.getName());

    // invocation handler
    private final Map<String, Method> methods = new HashMap<>();
    private Object target;

    // dao
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("pl.insert.example");

    public DynamicInvocationHandler(Object target) {
        this.target = target;

        for (Method method : target.getClass().getMethods()) {
            this.methods.put(method.getName(), method);
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        EntityManager entityManager = getEntityManager();

        Object result;

        Method implMethod = methods.get(method.getName());

        if (implMethod.isAnnotationPresent(Transactional.class) || method.isAnnotationPresent(Transactional.class)) {

            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            result = invokeMethod(implMethod, args, entityManager);

            if (transaction.isActive()) {
                transaction.commit();
            }

            logger.info("Invoked method: " + method.getName() + " in transactional mode.");

        } else {
            result = invokeMethod(implMethod, args, entityManager);

            logger.info("Invoked method: " + method.getName());
        }

        return result;
    }

    private Object invokeMethod(Method implMethod, Object[] args, EntityManager entityManager) throws IllegalAccessException, InvocationTargetException {

        ThreadLocal<EntityManager> threadLocal = EntityManagerHolder.getThreadLocal();
        Object result;

        try {
            threadLocal.set(entityManager);
            result = implMethod.invoke(target, args);

        } finally {
            threadLocal.remove();
        }
        return result;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
