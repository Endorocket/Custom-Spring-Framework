package pl.insert.spring.dynamicproxypattern;

import javax.persistence.EntityManager;

public class EntityManagerHolder {

    private static ThreadLocal<EntityManager> threadLocal = new ThreadLocal<>();

    public static ThreadLocal<EntityManager> getThreadLocal() {
        return threadLocal;
    }

    public static EntityManager getEntityManager() {

        EntityManager entityManager = threadLocal.get();

        if (entityManager == null) {
            throw new RuntimeException("Thread local doesn't have in storage entityManager object");
        }

        return entityManager;
    }
}
