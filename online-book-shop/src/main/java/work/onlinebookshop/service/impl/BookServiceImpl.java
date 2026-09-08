package work.onlinebookshop.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import work.onlinebookshop.dto.BookDto;
import work.onlinebookshop.dto.BookSearchParameterDto;
import work.onlinebookshop.dto.CreateBookRequestDto;
import work.onlinebookshop.exception.EntityNotFoundException;
import work.onlinebookshop.mapper.BookMapper;
import work.onlinebookshop.model.Book;
import work.onlinebookshop.repository.book.BookRepository;
import work.onlinebookshop.repository.book.BookSpecificationBuilder;
import work.onlinebookshop.service.BookService;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookSpecificationBuilder bookSpecificationBuilder;

    @Override
    public BookDto save(CreateBookRequestDto bookDto) {
        Book book = bookMapper.toEntity(bookDto);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto getById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Book not found by id: " + id));
        return bookMapper.toDto(book);
    }

    @Override
    public BookDto update(Long id, CreateBookRequestDto bookDto) {
        Book book = findBookById(id);
        bookMapper.updateBookFromDto(bookDto, book);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public void deleteById(Long id) {
        Book book = findBookById(id);
        bookRepository.delete(book);
    }

    private Book findBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Book not found by id: " + id));
    }

    @Override
    public List<BookDto> search(BookSearchParameterDto searchParam) {
        Specification<Book> bookSpecification = bookSpecificationBuilder.build(searchParam);
        return bookRepository.findAll(bookSpecification).stream()
                .map(bookMapper::toDto)
                .toList();
    }
}
