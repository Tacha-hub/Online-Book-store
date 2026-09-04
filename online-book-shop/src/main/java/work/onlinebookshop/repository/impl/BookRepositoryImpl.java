package work.onlinebookshop.repository.impl;

import java.util.List;
import work.onlinebookshop.model.Book;
import work.onlinebookshop.repository.BookRepository;

public class BookRepositoryImpl implements BookRepository {
    @Override
    public Book save(Book book) {
        return null;
    }

    @Override
    public List<Book> findAll() {
        return List.of();
    }
}
