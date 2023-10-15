package com.ai.bookstore.service;

import com.ai.bookstore.dao.BookDAO;
import com.ai.bookstore.dto.BookDTO;
import com.ai.bookstore.mapper.BookMapper;
import com.ai.bookstore.model.Book;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class BookServiceTest {

    @Mock
    private BookDAO bookDAO;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookService bookService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateBook_ShouldSaveBook() {
        // Arrange
        BookDTO bookDTO = new BookDTO();
        // Set properties in bookDTO

        Book book = new Book();
        // Set properties in book

        Mockito.when(bookMapper.dtoToBook(bookDTO)).thenReturn(book);
        Mockito.doNothing().when(bookDAO).saveOrUpdateBook(book);
        Mockito.when(bookMapper.bookToDto(book)).thenReturn(bookDTO);

        // Act
        BookDTO createdBook = bookService.createBook(bookDTO);

        // Assert
        assertEquals(bookDTO, createdBook);
    }

    @Test
    public void testUpdateBook_WithExistingBook_ShouldUpdateBook() {
        // Arrange
        Long bookId = 1L;

        BookDTO bookDTO = new BookDTO();
        // Set properties in bookDTO

        Book existingBook = new Book();
        // Set properties in existingBook

        Mockito.when(bookDAO.getBookById(bookId)).thenReturn(existingBook);
        Mockito.when(bookMapper.dtoToBook(bookDTO)).thenReturn(existingBook);
        Mockito.doNothing().when(bookDAO).saveOrUpdateBook(existingBook);
        Mockito.when(bookMapper.bookToDto(existingBook)).thenReturn(bookDTO);

        // Act
        BookDTO updatedBook = bookService.updateBook(bookId, bookDTO);

        // Assert
        assertEquals(bookDTO, updatedBook);
    }

    @Test
    public void testUpdateBook_WithNonExistentBook_ShouldReturnNull() {
        // Arrange
        Long bookId = 1L;

        Mockito.when(bookDAO.getBookById(bookId)).thenReturn(null);

        // Act
        BookDTO updatedBook = bookService.updateBook(bookId, new BookDTO());

        // Assert
        assertNull(updatedBook);
    }

    @Test
    public void testGetBook_WithExistingBook_ShouldReturnBook() {
        // Arrange
        Long bookId = 1L;

        BookDTO bookDTO = new BookDTO();
        // Set properties in bookDTO

        Book existingBook = new Book();
        // Set properties in existingBook

        Mockito.when(bookDAO.getBookById(bookId)).thenReturn(existingBook);
        Mockito.when(bookMapper.bookToDto(existingBook)).thenReturn(bookDTO);

        // Act
        BookDTO retrievedBook = bookService.getBook(bookId);

        // Assert
        assertEquals(bookDTO, retrievedBook);
    }

    @Test
    public void testGetBook_WithNonExistentBook_ShouldReturnNull() {
        // Arrange
        Long bookId = 1L;

        Mockito.when(bookDAO.getBookById(bookId)).thenReturn(null);

        // Act
        BookDTO retrievedBook = bookService.getBook(bookId);

        // Assert
        assertNull(retrievedBook);
    }
}
