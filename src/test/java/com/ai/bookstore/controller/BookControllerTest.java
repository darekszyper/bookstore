package com.ai.bookstore.controller;

import com.ai.bookstore.api.request.CreateBookRequest;
import com.ai.bookstore.api.request.UpdateBookRequest;
import com.ai.bookstore.api.response.BookResponse;
import com.ai.bookstore.dto.BookDTO;
import com.ai.bookstore.mapper.BookMapper;
import com.ai.bookstore.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class BookControllerTest {

    @Mock
    private BookService bookService;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookController bookController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetBook() {
        // Arrange
        Long bookId = 1L;
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(bookId);
        bookDTO.setTitle("Test Book");
        BookResponse expectedResponse = new BookResponse();
        expectedResponse.setId(bookId);
        expectedResponse.setTitle("Test Book");

        Mockito.when(bookService.getBook(bookId)).thenReturn(bookDTO);
        Mockito.when(bookMapper.dtoToResponse(bookDTO)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<BookResponse> response = bookController.getBook(bookId);

        // Assert
        assertEquals(expectedResponse, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetBookNotFound() {
        // Arrange
        Long bookId = 1L;
        Mockito.when(bookService.getBook(bookId)).thenReturn(null);

        // Act
        ResponseEntity<BookResponse> response = bookController.getBook(bookId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetAllBooks() {
        // Arrange
        List<BookDTO> bookDTOList = new ArrayList<>();
        BookDTO book1 = new BookDTO();
        book1.setId(1L);
        book1.setTitle("Book 1");
        BookDTO book2 = new BookDTO();
        book2.setId(2L);
        book2.setTitle("Book 2");
        bookDTOList.add(book1);
        bookDTOList.add(book2);

        BookResponse bookResponse1 = new BookResponse();
        bookResponse1.setId(1L);
        bookResponse1.setTitle("Book 1");
        BookResponse bookResponse2 = new BookResponse();
        bookResponse2.setId(2L);
        bookResponse2.setTitle("Book 2");

        List<BookResponse> expectedResponses = new ArrayList<>();
        expectedResponses.add(bookResponse1);
        expectedResponses.add(bookResponse2);

        Mockito.when(bookService.getAllBooks()).thenReturn(bookDTOList);
        Mockito.when(bookMapper.dtoToResponse(book1)).thenReturn(bookResponse1);
        Mockito.when(bookMapper.dtoToResponse(book2)).thenReturn(bookResponse2);

        // Act
        ResponseEntity<List<BookResponse> > response = bookController.getAllBooks();

        // Assert
        assertEquals(expectedResponses, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testCreateBook() {
        // Arrange
        CreateBookRequest createRequest = new CreateBookRequest();
        BookDTO bookDTO = new BookDTO();
        BookDTO createdBook = new BookDTO();
        createdBook.setId(1L);
        createdBook.setTitle("Test Book");

        BookResponse expectedResponse = new BookResponse();
        expectedResponse.setId(1L);
        expectedResponse.setTitle("Test Book");

        Mockito.when(bookMapper.requestToDto(createRequest)).thenReturn(bookDTO);
        Mockito.when(bookService.createBook(bookDTO)).thenReturn(createdBook);
        Mockito.when(bookMapper.dtoToResponse(createdBook)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<BookResponse> response = bookController.createBook(createRequest);

        // Assert
        assertEquals(expectedResponse, response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testUpdateBook() {
        // Arrange
        Long bookId = 1L;
        UpdateBookRequest updateRequest = new UpdateBookRequest();
        BookDTO bookDTO = new BookDTO();
        BookDTO updatedBook = new BookDTO();
        updatedBook.setId(1L);
        updatedBook.setTitle("Test Book Updated");

        BookResponse expectedResponse = new BookResponse();
        expectedResponse.setId(1L);
        expectedResponse.setTitle("Test Book Updated");

        Mockito.when(bookMapper.requestToDto(updateRequest)).thenReturn(bookDTO);
        Mockito.when(bookService.updateBook(bookId, bookDTO)).thenReturn(updatedBook);
        Mockito.when(bookMapper.dtoToResponse(updatedBook)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<BookResponse> response = bookController.updateBook(bookId, updateRequest);

        // Assert
        assertEquals(expectedResponse, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testUpdateBookNotFound() {
        // Arrange
        Long bookId = 1L;
        UpdateBookRequest updateRequest = new UpdateBookRequest();
        BookDTO bookDTO = new BookDTO();

        Mockito.when(bookMapper.requestToDto(updateRequest)).thenReturn(bookDTO);
        Mockito.when(bookService.updateBook(bookId, bookDTO)).thenReturn(null);

        // Act
        ResponseEntity<BookResponse> response = bookController.updateBook(bookId, updateRequest);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeleteBook() {
        // Arrange
        Long bookId = 1L;
        Mockito.when(bookService.deleteBook(bookId)).thenReturn(true);

        // Act
        ResponseEntity<Void> response = bookController.deleteBook(bookId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testDeleteBookNotFound() {
        // Arrange
        Long bookId = 1L;
        Mockito.when(bookService.deleteBook(bookId)).thenReturn(false);

        // Act
        ResponseEntity<Void> response = bookController.deleteBook(bookId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}