package demo.postgresjson.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Entity
@TypeDef(
        name = "jsonb",
        typeClass = JsonBinaryType.class
)
@NoArgsConstructor
@ToString
public class Book {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    private String isbn;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private String properties;

    @Builder
    public Book(String isbn, String properties) {
        this.isbn = isbn;
        this.properties = properties;
    }

    public void updateProperties(String properties) {
        this.properties = properties;
    }
}
