package work.onlinebookshop.mapper;

import org.mapstruct.Mapper;
import work.onlinebookshop.config.MapperConfig;
import work.onlinebookshop.dto.BookDto;
import work.onlinebookshop.dto.CreateBookRequestDto;
import work.onlinebookshop.model.Book;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDto toDto(Book book);

    Book toEntity(CreateBookRequestDto bookDto);
}
