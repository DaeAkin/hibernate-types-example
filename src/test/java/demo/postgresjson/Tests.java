package demo.postgresjson;

import demo.postgresjson.entity.Book;
import demo.postgresjson.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class Tests {
    @Autowired
    BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
        bookRepository.flush();
    }

    @Test
    void insert_test() {
        bookRepository.save(
                Book.builder()
                        .isbn("881-2938192")
                        .properties(
                                "{" +
                                        "   \"title\": \"High-Performance Java Persistence\"," +
                                        "   \"author\": \"Vlad Mihalcea\"," +
                                        "   \"publisher\": \"Amazon\"," +
                                        "   \"price\": 44.99" +
                                        "}"
                        ).build()

        );
    }

    @Test
    @Rollback(value = false)
    void update_test() {

        String properties = "{" +
                "   \"title\": \"High-Performance Java Persistence\"," +
                "   \"author\": \"Vlad Mihalcea\"," +
                "   \"publisher\": \"Amazon\"," +
                "   \"price\": 44.99" +
                "}";

        System.out.println("book insert occur");
        Book findBook = bookRepository.save(
                Book.builder()
                        .isbn("881-2938192")
                        .properties(
                                properties
                        ).build()
        );

        String updateProperties = "{" +
                "   \"title\": \"High-Performance Java Persistence\"," +
                "   \"author\": \"Vlad Mihalcea\"," +
                "   \"publisher\": \"Amazon\"," +
                "   \"price\": 144.99" +
                "}";

        System.out.println("book update occur");
        findBook.updateProperties(updateProperties);
    }

    @Test
    void select_json_test() {
        bookRepository.save(
                Book.builder()
                        .isbn("881-2938192")
                        .properties(
                                "{" +
                                        "   \"title\": \"High-Performance Java Persistence\"," +
                                        "   \"author\": \"Vlad Mihalcea\"," +
                                        "   \"publisher\": \"Amazon\"," +
                                        "   \"price\": 44.99" +
                                        "}"
                        ).build()

        );


        List<Book> book = bookRepository.findByPublisher("Amazon");

        System.out.println(book.toString());

    }
}
