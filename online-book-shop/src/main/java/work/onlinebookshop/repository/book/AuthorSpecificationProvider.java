package work.onlinebookshop.repository.book;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import work.onlinebookshop.model.Book;
import work.onlinebookshop.repository.SpecificationProvider;

@Component
public class AuthorSpecificationProvider implements SpecificationProvider<Book> {

    @Override
    public String getKey() {
        return "author";
    }

    public Specification<Book> getSpecification(String params) {
        return new Specification<Book>() {
            @Override
            public @Nullable Predicate toPredicate(Root<Book> root,
                                                   CriteriaQuery<?> query,
                                                   CriteriaBuilder criteriaBuilder) {
                return root.get("author").in(params);
            }
        };
    }
}
