package sv.edu.udb.bookapi.dto;

public record BookResponseDTO(
        Long id,
        String title,
        String author,
        Integer publicationYear,
        String description
) {}
