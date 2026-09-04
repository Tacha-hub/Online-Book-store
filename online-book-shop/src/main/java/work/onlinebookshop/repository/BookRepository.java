package work.onlinebookshop.repository;

import java.util.List;
import work.onlinebookshop.model.Book;

public interface BookRepository {
    Book save(Book book);

    List<Book> findAll();
}
