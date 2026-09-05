package work.onlinebookshop.repository.impl;

import org.hibernate.SessionFactory;

public abstract class AbstractRepository {
    protected final SessionFactory factory;

    protected AbstractRepository(SessionFactory sessionFactory) {
        this.factory = sessionFactory;
    }
}
