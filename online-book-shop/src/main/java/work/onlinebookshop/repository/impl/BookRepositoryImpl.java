package work.onlinebookshop.repository.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import work.onlinebookshop.exception.DataProcessingException;
import work.onlinebookshop.model.Book;
import work.onlinebookshop.repository.BookRepository;

@Repository
public class BookRepositoryImpl extends AbstractRepository implements BookRepository {
    public BookRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Book save(Book book) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = factory.openSession();
            transaction = session.beginTransaction();

            session.persist(book);
            transaction.commit();
            return book;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't save book to database: " + book, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Book> findAll() {
        Transaction transaction = null;
        Session session = null;

        try {
            session = factory.openSession();
            transaction = session.beginTransaction();

            List<Book> books = session
                    .createSelectionQuery("from Book", Book.class)
                    .getResultList();
            transaction.commit();
            return books;
        } catch (java.lang.Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't find all books in database", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
