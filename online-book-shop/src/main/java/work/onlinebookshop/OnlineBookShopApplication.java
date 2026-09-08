package work.onlinebookshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import work.onlinebookshop.service.BookService;

@SpringBootApplication
public class OnlineBookShopApplication {
    private final BookService bookService;

    public OnlineBookShopApplication(BookService bookService) {
        this.bookService = bookService;
    }

    public static void main(String[] args) {
        SpringApplication.run(OnlineBookShopApplication.class, args);
    }

}
