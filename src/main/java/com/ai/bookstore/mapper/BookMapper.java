package com.ai.bookstore.mapper;

import com.ai.bookstore.api.request.CreateBookRequest;
import com.ai.bookstore.api.request.UpdateBookRequest;
import com.ai.bookstore.api.response.BookResponse;
import com.ai.bookstore.dto.AuthorDTO;
import com.ai.bookstore.dto.BookDTO;
import com.ai.bookstore.dto.GenreDTO;
import com.ai.bookstore.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    private AuthorMapper authorMapper;
    private GenreMapper genreMapper;

    public BookMapper() {
    }

    public BookMapper(AuthorMapper authorMapper, GenreMapper genreMapper) {
        this.authorMapper = authorMapper;
        this.genreMapper = genreMapper;
    }

    public BookDTO bookToDto(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setPrice(book.getPrice());
        bookDTO.setQuantityAvailable(book.getQuantityAvailable());
        bookDTO.setAuthor(authorMapper.authorToDto(book.getAuthor()));
        bookDTO.setGenre(genreMapper.genreToDto(book.getGenre()));
        return bookDTO;
    }

    public Book dtoToBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setPrice(bookDTO.getPrice());
        book.setQuantityAvailable(bookDTO.getQuantityAvailable());
        book.setAuthor(authorMapper.dtoToAuthor(bookDTO.getAuthor()));
        book.setGenre(genreMapper.dtoToGenre(bookDTO.getGenre()));
        return book;
    }

    public BookDTO requestToDto(CreateBookRequest request) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle(request.getTitle());
        bookDTO.setPrice(request.getPrice());
        bookDTO.setQuantityAvailable(request.getQuantityAvailable());
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(request.getAuthorId());
        bookDTO.setAuthor(authorDTO);
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setId(request.getGenreId());
        bookDTO.setGenre(genreDTO);
        return bookDTO;
    }

    public BookDTO requestToDto(UpdateBookRequest request) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle(request.getTitle());
        bookDTO.setPrice(request.getPrice());
        bookDTO.setQuantityAvailable(request.getQuantityAvailable());
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(request.getAuthorId());
        bookDTO.setAuthor(authorDTO);
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setId(request.getGenreId());
        bookDTO.setGenre(genreDTO);
        return bookDTO;
    }


    public BookResponse dtoToResponse(BookDTO bookDTO) {
        BookResponse response = new BookResponse();
        response.setId(bookDTO.getId());
        response.setTitle(bookDTO.getTitle());
        response.setPrice(bookDTO.getPrice());
        response.setQuantityAvailable(bookDTO.getQuantityAvailable());
        response.setAuthor(bookDTO.getAuthor());
        response.setGenre(bookDTO.getGenre());
        return response;
    }
}