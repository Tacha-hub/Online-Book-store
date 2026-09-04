package work.onlinebookshop.service;

import java.util.List;
import work.onlinebookshop.model.Book;

public interface BookService {
    Book save(Book book);

    List<Book> findAll();
}
