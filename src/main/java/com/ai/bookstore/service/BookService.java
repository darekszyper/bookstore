package com.ai.bookstore.service;

import com.ai.bookstore.dao.BookDAO;
import com.ai.bookstore.dto.BookDTO;
import com.ai.bookstore.mapper.BookMapper;
import com.ai.bookstore.model.Book;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private BookDAO bookDAO;
    private BookMapper bookMapper;

    public BookService() {
    }

    public BookService(BookDAO bookDAO, BookMapper bookMapper) {
        this.bookDAO = bookDAO;
        this.bookMapper = bookMapper;
    }

    @Transactional
    public BookDTO createBook(BookDTO bookDTO) {
        // Map DTO to entity
        Book book = bookMapper.dtoToBook(bookDTO);

        // Save the entity in the database
        bookDAO.saveOrUpdateBook(book);

        // Map the saved entity back to DTO
        return bookMapper.bookToDto(book);
    }

    @Transactional
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        // Retrieve the book by ID
        Book existingBook = bookDAO.getBookById(id);

        if (existingBook == null) {
            // Handle the case when the book is not found
            return null;
        }

        // Map DTO to the existing entity
        bookDTO.setId(existingBook.getId());
        Book updatedBook = bookMapper.dtoToBook(bookDTO);

        // Update the entity in the database
        bookDAO.saveOrUpdateBook(updatedBook);

        // Map the updated entity back to DTO
        return bookMapper.bookToDto(updatedBook);
    }

    @Transactional
    public BookDTO getBook(Long id) {
        // Retrieve the book by ID
        Book book = bookDAO.getBookById(id);

        if (book == null) {
            // Handle the case when the book is not found
            return null;
        }

        // Map the entity to DTO
        return bookMapper.bookToDto(book);
    }

    @Transactional
    public List<BookDTO> getAllBooks() {
        return bookDAO.getAllBooks().stream().map(bookMapper::bookToDto).collect(Collectors.toList());
    }

    @Transactional
    public boolean deleteBook(Long id) {
        BookDTO bookDTO = bookMapper.bookToDto(bookDAO.getBookById(id));
        if (bookDTO != null) {
            bookDAO.deleteBook(bookMapper.dtoToBook(bookDTO));
            return true;
        }
        return false;
    }

    public List<BookDTO> searchBooks(String keyword) {
        // Use the BookDAO's searchBooks method to retrieve books based on the keyword.
        List<Book> books = bookDAO.searchBooks(keyword);

        // Map the list of Book entities to a list of BookDTOs.
        return books.stream()
                .map(bookMapper::bookToDto)
                .collect(Collectors.toList());
    }
}

