package pl.insert.hibernate;

import org.hibernate.Session;

@FunctionalInterface
public interface TransactionCallback<T> {

    T doInTransaction(Session session);
}
