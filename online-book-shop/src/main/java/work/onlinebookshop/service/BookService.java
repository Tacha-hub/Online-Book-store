package work.onlinebookshop.service;

import java.util.List;
import work.onlinebookshop.dto.BookDto;
import work.onlinebookshop.dto.CreateBookRequestDto;

public interface BookService {
    BookDto save(CreateBookRequestDto bookDto);

    List<BookDto> findAll();

    BookDto getById(Long id);
}
