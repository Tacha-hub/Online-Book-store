package work.onlinebookshop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import work.onlinebookshop.dto.BookDto;
import work.onlinebookshop.dto.BookSearchParameterDto;
import work.onlinebookshop.dto.CreateBookRequestDto;

public interface BookService {
    BookDto save(CreateBookRequestDto bookDto);

    Page<BookDto> findAll(Pageable pageable);

    BookDto getById(Long id);

    BookDto update(Long id, CreateBookRequestDto bookDto);

    void deleteById(Long id);

    Page<BookDto> search(BookSearchParameterDto searchParams, Pageable pageable);
}
