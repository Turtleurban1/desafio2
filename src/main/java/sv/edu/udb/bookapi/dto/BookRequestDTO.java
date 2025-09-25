package sv.edu.udb.bookapi.dto;

import jakarta.validation.constraints.*;

public record BookRequestDTO(
        @NotBlank @Size(max = 75) String title,
        @NotBlank @Size(max = 50) String author,
        @Min(1900) @Max(2025) Integer publicationYear,
        @Size(max = 200) String description
) {}
