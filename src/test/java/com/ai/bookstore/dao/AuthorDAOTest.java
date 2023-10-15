package com.ai.bookstore.dao;

import com.ai.bookstore.model.Author;
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

public class AuthorDAOTest {

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @InjectMocks
    private AuthorDAO authorDAO;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(sessionFactory.getCurrentSession()).thenReturn(session);
    }

    @Test
    public void testSaveOrUpdateAuthor() {
        // Arrange
        Author author = new Author();
        Mockito.doNothing().when(session).saveOrUpdate(author);

        // Act
        authorDAO.saveOrUpdateAuthor(author);

        // Assert
        Mockito.verify(session).saveOrUpdate(author);
    }

    @Test
    public void testGetAuthorById() {
        // Arrange
        Long authorId = 1L;
        Author expectedAuthor = new Author();
        Mockito.when(session.get(Author.class, authorId)).thenReturn(expectedAuthor);

        // Act
        Author retrievedAuthor = authorDAO.getAuthorById(authorId);

        // Assert
        assertEquals(expectedAuthor, retrievedAuthor);
    }

    @Test
    public void testGetAuthorByIdNotFound() {
        // Arrange
        Long authorId = 1L;
        Mockito.when(session.get(Author.class, authorId)).thenReturn(null);

        // Act
        Author retrievedAuthor = authorDAO.getAuthorById(authorId);

        // Assert
        assertNull(retrievedAuthor);
    }

    @Test
    public void testGetAllAuthors() {
        // Arrange
        Query<Author> query = Mockito.mock(Query.class);
        List<Author> expectedAuthors = new ArrayList<>();
        expectedAuthors.add(new Author());
        Mockito.when(session.createQuery("FROM Author", Author.class)).thenReturn(query);
        Mockito.when(query.list()).thenReturn(expectedAuthors);

        // Act
        List<Author> retrievedAuthors = authorDAO.getAllAuthors();

        // Assert
        assertEquals(expectedAuthors, retrievedAuthors);
    }
}