package pl.insert.spring.dynamicproxypattern;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;
import java.util.List;
import java.util.Map;

public class EntityManagerProxy implements EntityManager {

    @Override
    public void persist(Object o) {
        EntityManagerHolder.getEntityManager().persist(o);
    }

    @Override
    public <T> T merge(T t) {
        return EntityManagerHolder.getEntityManager().merge(t);
    }

    @Override
    public void remove(Object o) {
        EntityManagerHolder.getEntityManager().remove(o);
    }

    @Override
    public <T> T find(Class<T> aClass, Object o) {
        return EntityManagerHolder.getEntityManager().find(aClass, o);
    }

    @Override
    public <T> T find(Class<T> aClass, Object o, Map<String, Object> map) {
        return EntityManagerHolder.getEntityManager().find(aClass, o, map);
    }

    @Override
    public <T> T find(Class<T> aClass, Object o, LockModeType lockModeType) {
        return EntityManagerHolder.getEntityManager().find(aClass, o, lockModeType);
    }

    @Override
    public <T> T find(Class<T> aClass, Object o, LockModeType lockModeType, Map<String, Object> map) {
        return EntityManagerHolder.getEntityManager().find(aClass, o, lockModeType, map);
    }

    @Override
    public <T> T getReference(Class<T> aClass, Object o) {
        return EntityManagerHolder.getEntityManager().getReference(aClass, o);
    }

    @Override
    public void flush() {
        EntityManagerHolder.getEntityManager().flush();
    }

    @Override
    public void setFlushMode(FlushModeType flushModeType) {
        EntityManagerHolder.getEntityManager().setFlushMode(flushModeType);
    }

    @Override
    public FlushModeType getFlushMode() {
        return EntityManagerHolder.getEntityManager().getFlushMode();
    }

    @Override
    public void lock(Object o, LockModeType lockModeType) {
        EntityManagerHolder.getEntityManager().lock(o, lockModeType);
    }

    @Override
    public void lock(Object o, LockModeType lockModeType, Map<String, Object> map) {
        EntityManagerHolder.getEntityManager().lock(o, lockModeType, map);
    }

    @Override
    public void refresh(Object o) {
        EntityManagerHolder.getEntityManager().refresh(o);
    }

    @Override
    public void refresh(Object o, Map<String, Object> map) {
        EntityManagerHolder.getEntityManager().refresh(o, map);
    }

    @Override
    public void refresh(Object o, LockModeType lockModeType) {
        EntityManagerHolder.getEntityManager().refresh(o, lockModeType);
    }

    @Override
    public void refresh(Object o, LockModeType lockModeType, Map<String, Object> map) {
        EntityManagerHolder.getEntityManager().refresh(o, lockModeType, map);
    }

    @Override
    public void clear() {
        EntityManagerHolder.getEntityManager().clear();
    }

    @Override
    public void detach(Object o) {
        EntityManagerHolder.getEntityManager().detach(o);
    }

    @Override
    public boolean contains(Object o) {
        return EntityManagerHolder.getEntityManager().contains(o);
    }

    @Override
    public LockModeType getLockMode(Object o) {
        return EntityManagerHolder.getEntityManager().getLockMode(o);
    }

    @Override
    public void setProperty(String s, Object o) {
        EntityManagerHolder.getEntityManager().setProperty(s, o);
    }

    @Override
    public Map<String, Object> getProperties() {
        return EntityManagerHolder.getEntityManager().getProperties();
    }

    @Override
    public Query createQuery(String s) {
        return EntityManagerHolder.getEntityManager().createQuery(s);
    }

    @Override
    public <T> TypedQuery<T> createQuery(CriteriaQuery<T> criteriaQuery) {
        return EntityManagerHolder.getEntityManager().createQuery(criteriaQuery);
    }

    @Override
    public Query createQuery(CriteriaUpdate criteriaUpdate) {
        return EntityManagerHolder.getEntityManager().createQuery(criteriaUpdate);
    }

    @Override
    public Query createQuery(CriteriaDelete criteriaDelete) {
        return EntityManagerHolder.getEntityManager().createQuery(criteriaDelete);
    }

    @Override
    public <T> TypedQuery<T> createQuery(String s, Class<T> aClass) {
        return EntityManagerHolder.getEntityManager().createQuery(s, aClass);
    }

    @Override
    public Query createNamedQuery(String s) {
        return EntityManagerHolder.getEntityManager().createNamedQuery(s);
    }

    @Override
    public <T> TypedQuery<T> createNamedQuery(String s, Class<T> aClass) {
        return EntityManagerHolder.getEntityManager().createNamedQuery(s, aClass);
    }

    @Override
    public Query createNativeQuery(String s) {
        return EntityManagerHolder.getEntityManager().createNativeQuery(s);
    }

    @Override
    public Query createNativeQuery(String s, Class aClass) {
        return EntityManagerHolder.getEntityManager().createNativeQuery(s, aClass);
    }

    @Override
    public Query createNativeQuery(String s, String s1) {
        return EntityManagerHolder.getEntityManager().createNativeQuery(s1);
    }

    @Override
    public StoredProcedureQuery createNamedStoredProcedureQuery(String s) {
        return EntityManagerHolder.getEntityManager().createNamedStoredProcedureQuery(s);
    }

    @Override
    public StoredProcedureQuery createStoredProcedureQuery(String s) {
        return EntityManagerHolder.getEntityManager().createStoredProcedureQuery(s);
    }

    @Override
    public StoredProcedureQuery createStoredProcedureQuery(String s, Class... classes) {
        return EntityManagerHolder.getEntityManager().createStoredProcedureQuery(s, classes);
    }

    @Override
    public StoredProcedureQuery createStoredProcedureQuery(String s, String... strings) {
        return EntityManagerHolder.getEntityManager().createStoredProcedureQuery(s, strings);
    }

    @Override
    public void joinTransaction() {
        EntityManagerHolder.getEntityManager().joinTransaction();
    }

    @Override
    public boolean isJoinedToTransaction() {
        return EntityManagerHolder.getEntityManager().isJoinedToTransaction();
    }

    @Override
    public <T> T unwrap(Class<T> aClass) {
        return EntityManagerHolder.getEntityManager().unwrap(aClass);
    }

    @Override
    public Object getDelegate() {
        return EntityManagerHolder.getEntityManager().getDelegate();
    }

    @Override
    public void close() {
        EntityManagerHolder.getEntityManager().close();
    }

    @Override
    public boolean isOpen() {
        return EntityManagerHolder.getEntityManager().isOpen();
    }

    @Override
    public EntityTransaction getTransaction() {
        return EntityManagerHolder.getEntityManager().getTransaction();
    }

    @Override
    public EntityManagerFactory getEntityManagerFactory() {
        return EntityManagerHolder.getEntityManager().getEntityManagerFactory();
    }

    @Override
    public CriteriaBuilder getCriteriaBuilder() {
        return EntityManagerHolder.getEntityManager().getCriteriaBuilder();
    }

    @Override
    public Metamodel getMetamodel() {
        return EntityManagerHolder.getEntityManager().getMetamodel();
    }

    @Override
    public <T> EntityGraph<T> createEntityGraph(Class<T> aClass) {
        return EntityManagerHolder.getEntityManager().createEntityGraph(aClass);
    }

    @Override
    public EntityGraph<?> createEntityGraph(String s) {
        return EntityManagerHolder.getEntityManager().createEntityGraph(s);
    }

    @Override
    public EntityGraph<?> getEntityGraph(String s) {
        return EntityManagerHolder.getEntityManager().getEntityGraph(s);
    }

    @Override
    public <T> List<EntityGraph<? super T>> getEntityGraphs(Class<T> aClass) {
        return EntityManagerHolder.getEntityManager().getEntityGraphs(aClass);
    }
}
