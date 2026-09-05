package work.onlinebookshop;

import java.math.BigDecimal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import work.onlinebookshop.model.Book;
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

    @Bean
    public CommandLineRunner unit() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Book firstTestBook = new Book();
                firstTestBook.setTitle("firstTestBook");
                firstTestBook.setAuthor("firstTestAuthor");
                firstTestBook.setIsbn("firstTestIsbn");
                firstTestBook.setPrice(BigDecimal.valueOf(10));
                firstTestBook.setDescription("firstTestDescription");
                firstTestBook.setCoverImage("firstTestCoverImage");

                bookService.save(firstTestBook);
                bookService.findAll().forEach(book ->
                        System.out.println(book.getTitle() + " - " + book.getAuthor()));
            }
        };
    }
}
