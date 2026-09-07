package work.onlinebookshop.repository.book;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import work.onlinebookshop.model.Book;
import work.onlinebookshop.repository.SpecificationProvider;
import work.onlinebookshop.repository.SpecificationProviderManager;

@RequiredArgsConstructor
@Component
public class BookSpecificationProviderManager implements SpecificationProviderManager<Book> {
    private final List<SpecificationProvider<Book>> bookSpecificationProviders;

    @Override
    public SpecificationProvider<Book> getSpecificationProvider(String key) {
        return bookSpecificationProviders.stream()
                .filter(s -> s.getKey().equals(key))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cannot find specification by key: "
                        + key));
    }
}
