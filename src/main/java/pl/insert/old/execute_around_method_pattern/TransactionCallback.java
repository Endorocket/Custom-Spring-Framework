package pl.insert.old.execute_around_method_pattern;

import org.hibernate.Session;

@FunctionalInterface
public interface TransactionCallback<T> {

    T doInTransaction(Session session);
}
