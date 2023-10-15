package com.ai.bookstore.dao;

import com.ai.bookstore.model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class BookDAOTest {

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @InjectMocks
    private BookDAO bookDAO;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(sessionFactory.getCurrentSession()).thenReturn(session);
    }

    @Test
    public void testSaveOrUpdateBook() {
        // Arrange
        Book book = new Book();
        Mockito.doNothing().when(session).saveOrUpdate(book);

        // Act
        bookDAO.saveOrUpdateBook(book);

        // Assert
        Mockito.verify(session).saveOrUpdate(book);
    }

    @Test
    public void testGetBookById() {
        // Arrange
        Long bookId = 1L;
        Book expectedBook = new Book();
        Mockito.when(session.get(Book.class, bookId)).thenReturn(expectedBook);

        // Act
        Book retrievedBook = bookDAO.getBookById(bookId);

        // Assert
        assertEquals(expectedBook, retrievedBook);
    }

    @Test
    public void testGetBookByIdNotFound() {
        // Arrange
        Long bookId = 1L;
        Mockito.when(session.get(Book.class, bookId)).thenReturn(null);

        // Act
        Book retrievedBook = bookDAO.getBookById(bookId);

        // Assert
        assertNull(retrievedBook);
    }

    @Test
    public void testGetAllBooks() {
        // Arrange
        Query<Book> query = Mockito.mock(Query.class);
        List<Book> expectedBooks = new ArrayList<>();
        expectedBooks.add(new Book());
        Mockito.when(session.createQuery("FROM Book", Book.class)).thenReturn(query);
        Mockito.when(query.list()).thenReturn(expectedBooks);

        // Act
        List<Book> retrievedBooks = bookDAO.getAllBooks();

        // Assert
        assertEquals(expectedBooks, retrievedBooks);
    }

    @Test
    public void testSearchBooks() {
        // Arrange
        String keyword = "example";
        Query<Book> query = Mockito.mock(Query.class);
        List<Book> expectedBooks = new ArrayList<>();
        expectedBooks.add(new Book());

        Mockito.when(session.createQuery(Mockito.anyString(), Mockito.eq(Book.class))).thenReturn(query);
        Mockito.when(query.setParameter("keyword", "%example%")).thenReturn(query);
        Mockito.when(query.list()).thenReturn(expectedBooks);

        // Act
        List<Book> retrievedBooks = bookDAO.searchBooks(keyword);

        // Assert
        assertEquals(expectedBooks, retrievedBooks);
    }

    @Test
    public void testSearchBooksNoResults() {
        // Arrange
        String keyword = "nonexistent";
        Query<Book> query = Mockito.mock(Query.class);
        List<Book> expectedBooks = new ArrayList<>();

        Mockito.when(session.createQuery(Mockito.anyString(), Mockito.eq(Book.class))).thenReturn(query);
        Mockito.when(query.setParameter("keyword", "%nonexistent%")).thenReturn(query);
        Mockito.when(query.list()).thenReturn(expectedBooks);

        // Act
        List<Book> retrievedBooks = bookDAO.searchBooks(keyword);

        // Assert
        assertEquals(expectedBooks, retrievedBooks);
    }
}