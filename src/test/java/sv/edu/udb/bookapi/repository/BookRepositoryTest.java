package sv.edu.udb.bookapi.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import sv.edu.udb.bookapi.model.Book;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testSaveAndFindById() {
        Book book = Book.builder()
                .title("Clean Code")
                .author("Robert C. Martin")
                .publicationYear(2008)
                .description("A book about writing clean code")
                .build();

        Book saved = bookRepository.save(book);

        assertNotNull(saved.getId());
        assertEquals("Clean Code", saved.getTitle());

        Book found = bookRepository.findById(saved.getId()).orElse(null);
        assertNotNull(found);
        assertEquals("Clean Code", found.getTitle());
    }

    @Test
    void testFindByTitleContainingIgnoreCase() {
        Book book = Book.builder()
                .title("Spring in Action")
                .author("Craig Walls")
                .publicationYear(2021)
                .build();

        bookRepository.save(book);

        Page<Book> result = bookRepository.findByTitleContainingIgnoreCase(
                "spring", PageRequest.of(0, 10));

        assertFalse(result.isEmpty());
        assertEquals("Spring in Action", result.getContent().get(0).getTitle());
    }
}
