package work.onlinebookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import work.onlinebookshop.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
