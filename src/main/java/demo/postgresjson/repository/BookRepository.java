package demo.postgresjson.repository;

import demo.postgresjson.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    Optional<Book> findByProperties(String properties);

    @Query(value = "select * from book where properties ->> 'publisher' = :publisher",nativeQuery = true)
    List<Book> findByPublisher(@Param("publisher") String publisher);
}
