package work.onlinebookshop.repository;

import org.springframework.data.jpa.domain.Specification;
import work.onlinebookshop.dto.BookSearchParameterDto;

public interface SpecificationBuilder<T> {
    Specification<T> build(BookSearchParameterDto searchParametr);
}
