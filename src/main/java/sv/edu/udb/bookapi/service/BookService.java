package sv.edu.udb.bookapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sv.edu.udb.bookapi.dto.BookRequestDTO;
import sv.edu.udb.bookapi.dto.BookResponseDTO;
import sv.edu.udb.bookapi.model.Book;
import sv.edu.udb.bookapi.repository.BookRepository;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;

    // Listar con paginación y filtro opcional
    public Page<BookResponseDTO> list(String title, Pageable pageable) {
        Page<Book> page = (title == null || title.isBlank())
                ? repository.findAll(pageable)
                : repository.findByTitleContainingIgnoreCase(title, pageable);

        return page.map(this::toResponse);
    }

    // Crear libro
    public BookResponseDTO create(BookRequestDTO dto) {
        Book saved = repository.save(toEntity(dto));
        return toResponse(saved);
    }

    // Actualizar libro
    public BookResponseDTO update(Long id, BookRequestDTO dto) {
        Book book = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Book " + id + " not found"));

        book.setTitle(dto.title());
        book.setAuthor(dto.author());
        book.setPublicationYear(dto.publicationYear());
        book.setDescription(dto.description());

        return toResponse(repository.save(book));
    }

    // Eliminar libro
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Book " + id + " not found");
        }
        repository.deleteById(id);
    }

    // Helpers: Entity <-> DTO
    private Book toEntity(BookRequestDTO dto) {
        Book b = new Book();
        b.setTitle(dto.title());
        b.setAuthor(dto.author());
        b.setPublicationYear(dto.publicationYear());
        b.setDescription(dto.description());
        return b;
    }

    private BookResponseDTO toResponse(Book b) {
        return new BookResponseDTO(
                b.getId(), b.getTitle(), b.getAuthor(),
                b.getPublicationYear(), b.getDescription()
        );
    }
}
