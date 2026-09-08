package work.onlinebookshop.repository.book;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import work.onlinebookshop.dto.BookSearchParameterDto;
import work.onlinebookshop.model.Book;
import work.onlinebookshop.repository.SpecificationBuilder;
import work.onlinebookshop.repository.SpecificationProviderManager;

@RequiredArgsConstructor
@Component
public class BookSpecificationBuilder implements SpecificationBuilder<Book> {
    private final SpecificationProviderManager<Book> bookSpecificationProviderManager;

    @Override
    public Specification<Book> build(BookSearchParameterDto searchParametr) {
        Specification<Book> spec = Specification.allOf();
        if (searchParametr.title() != null && !searchParametr.title().isBlank()) {
            spec = spec.and(bookSpecificationProviderManager.getSpecificationProvider("title")
                    .getSpecification(searchParametr.title()));
        }

        if (searchParametr.author() != null && !searchParametr.author().isBlank()) {
            spec = spec.and(bookSpecificationProviderManager.getSpecificationProvider("author")
                    .getSpecification(searchParametr.author()));
        }
        return spec;
    }
}
