package pl.insert.dao.dynamic_proxy_pattern;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.lang.reflect.InvocationHandler;
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
        EntityTransaction transaction = entityManager.getTransaction();

        ThreadLocal<EntityManager> threadLocal = EntityManagerHolder.getThreadLocal();

        Object result;

        transaction.begin();

//        method.isAnnotationPresent()

        try {
            threadLocal.set(entityManager);
            result = methods.get(method.getName()).invoke(target, args);

        } finally {
            threadLocal.remove();
        }

        if (transaction.isActive()) {
            transaction.commit();
        }

        logger.info("Invoked method: " + method.getName());

        return result;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

}
