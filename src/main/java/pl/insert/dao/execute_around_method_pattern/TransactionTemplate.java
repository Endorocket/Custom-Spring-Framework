package pl.insert.dao.execute_around_method_pattern;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.insert.hibernate.HibernateUtil;

public class TransactionTemplate {

    public <T> T execute(TransactionCallback<T> action) {

        Session session = null;
        Transaction transaction = null;
        T toReturn = null;

        try {
            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();

            toReturn = action.doInTransaction(session);

        } catch (Exception ex) {
            ex.printStackTrace();
            // handle exception here
            if (transaction != null) transaction.rollback();
        } finally {
            try {
                if (session != null) session.close();
            } catch (Exception ex) {
            }
        }

        return toReturn;
    }
}
