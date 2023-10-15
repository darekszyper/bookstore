package com.ai.bookstore.mapper;

import com.ai.bookstore.api.request.CreateBookRequest;
import com.ai.bookstore.api.request.UpdateBookRequest;
import com.ai.bookstore.api.response.BookResponse;
import com.ai.bookstore.dto.AuthorDTO;
import com.ai.bookstore.dto.BookDTO;
import com.ai.bookstore.dto.GenreDTO;
import com.ai.bookstore.model.Author;
import com.ai.bookstore.model.Book;
import com.ai.bookstore.model.Genre;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class BookMapperTest {

    private final AuthorMapper authorMapper = new AuthorMapper();
    private final GenreMapper genreMapper = new GenreMapper();
    private final BookMapper bookMapper = new BookMapper(authorMapper, genreMapper);

    @Test
    public void testBookToDto() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Sample Book");
        book.setPrice(19.99);
        book.setQuantityAvailable(50);

        Author author = new Author();
        author.setId(1L);
        author.setName("John Doe");
        book.setAuthor(author);

        Genre genre = new Genre();
        genre.setId(1L);
        genre.setName("Fiction");
        book.setGenre(genre);

        BookDTO bookDTO = bookMapper.bookToDto(book);

        assertEquals(book.getId(), bookDTO.getId());
        assertEquals(book.getTitle(), bookDTO.getTitle());
        assertEquals(book.getPrice(), bookDTO.getPrice(), 0.001); // Use delta for floating-point comparison
        assertEquals(book.getAuthor().getName(), bookDTO.getAuthor().getName());
        assertEquals(book.getGenre().getName(), bookDTO.getGenre().getName());
    }

    @Test
    public void testDtoToBook() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(1L);
        bookDTO.setTitle("Sample Book");
        bookDTO.setPrice(19.99);
        bookDTO.setQuantityAvailable(50);

        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(1L);
        authorDTO.setName("John Doe");
        bookDTO.setAuthor(authorDTO);

        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setId(1L);
        genreDTO.setName("Fiction");
        bookDTO.setGenre(genreDTO);

        Book book = bookMapper.dtoToBook(bookDTO);

        assertEquals(bookDTO.getId(), book.getId());
        assertEquals(bookDTO.getTitle(), book.getTitle());
        assertEquals(bookDTO.getPrice(), book.getPrice(), 0.001); // Use delta for floating-point comparison
        assertEquals(bookDTO.getAuthor().getName(), book.getAuthor().getName());
        assertEquals(bookDTO.getGenre().getName(), book.getGenre().getName());
    }

    @Test
    public void testRequestToDtoCreateBook() {
        CreateBookRequest request = new CreateBookRequest();
        request.setTitle("New Book");
        request.setPrice(24.99);
        request.setQuantityAvailable(30);
        request.setAuthorId(1L);
        request.setGenreId(1L);

        BookDTO bookDTO = bookMapper.requestToDto(request);

        assertEquals(request.getTitle(), bookDTO.getTitle());
        assertEquals(request.getPrice(), bookDTO.getPrice(), 0.001); // Use delta for floating-point comparison
        assertEquals(request.getQuantityAvailable(), bookDTO.getQuantityAvailable());
        assertEquals(request.getAuthorId(), bookDTO.getAuthor().getId());
        assertEquals(request.getGenreId(), bookDTO.getGenre().getId());
    }

    @Test
    public void testRequestToDtoUpdateBook() {
        UpdateBookRequest request = new UpdateBookRequest();
        request.setTitle("Updated Book");
        request.setPrice(29.99);
        request.setQuantityAvailable(40);
        request.setAuthorId(2L);
        request.setGenreId(2L);

        BookDTO bookDTO = bookMapper.requestToDto(request);

        assertEquals(request.getTitle(), bookDTO.getTitle());
        assertEquals(request.getPrice(), bookDTO.getPrice(), 0.001); // Use delta for floating-point comparison
        assertEquals(request.getQuantityAvailable(), bookDTO.getQuantityAvailable());
        assertEquals(request.getAuthorId(), bookDTO.getAuthor().getId());
        assertEquals(request.getGenreId(), bookDTO.getGenre().getId());
    }

    @Test
    public void testDtoToResponse() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(1L);
        bookDTO.setTitle("Sample Book");
        bookDTO.setPrice(19.99);
        bookDTO.setQuantityAvailable(50);

        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(1L);
        authorDTO.setName("John Doe");
        bookDTO.setAuthor(authorDTO);

        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setId(1L);
        genreDTO.setName("Fiction");
        bookDTO.setGenre(genreDTO);

        BookResponse response = bookMapper.dtoToResponse(bookDTO);

        assertEquals(bookDTO.getId(), response.getId());
        assertEquals(bookDTO.getTitle(), response.getTitle());
        assertEquals(bookDTO.getPrice(), response.getPrice(), 0.001); // Use delta for floating-point comparison
        assertEquals(bookDTO.getQuantityAvailable(), response.getQuantityAvailable());
        assertEquals(bookDTO.getAuthor().getName(), response.getAuthor().getName());
        assertEquals(bookDTO.getGenre().getName(), response.getGenre().getName());
    }
}