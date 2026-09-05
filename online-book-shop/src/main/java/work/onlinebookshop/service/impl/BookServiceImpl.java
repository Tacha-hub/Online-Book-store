package work.onlinebookshop.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import work.onlinebookshop.model.Book;
import work.onlinebookshop.repository.BookRepository;
import work.onlinebookshop.service.BookService;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}
