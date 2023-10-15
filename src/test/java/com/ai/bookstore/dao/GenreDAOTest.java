package com.ai.bookstore.dao;

import com.ai.bookstore.model.Genre;
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

public class GenreDAOTest {

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @InjectMocks
    private GenreDAO genreDAO;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(sessionFactory.getCurrentSession()).thenReturn(session);
    }

    @Test
    public void testSaveOrUpdateGenre() {
        // Arrange
        Genre genre = new Genre();
        Mockito.doNothing().when(session).saveOrUpdate(genre);

        // Act
        genreDAO.saveOrUpdateGenre(genre);

        // Assert
        Mockito.verify(session).saveOrUpdate(genre);
    }

    @Test
    public void testGetGenreById() {
        // Arrange
        Long genreId = 1L;
        Genre expectedGenre = new Genre();
        Mockito.when(session.get(Genre.class, genreId)).thenReturn(expectedGenre);

        // Act
        Genre retrievedGenre = genreDAO.getGenreById(genreId);

        // Assert
        assertEquals(expectedGenre, retrievedGenre);
    }

    @Test
    public void testGetGenreByIdNotFound() {
        // Arrange
        Long genreId = 1L;
        Mockito.when(session.get(Genre.class, genreId)).thenReturn(null);

        // Act
        Genre retrievedGenre = genreDAO.getGenreById(genreId);

        // Assert
        assertNull(retrievedGenre);
    }

    @Test
    public void testGetAllGenres() {
        // Arrange
        Query<Genre> query = Mockito.mock(Query.class);
        List<Genre> expectedGenres = new ArrayList<>();
        expectedGenres.add(new Genre());
        Mockito.when(session.createQuery("FROM Genre", Genre.class)).thenReturn(query);
        Mockito.when(query.list()).thenReturn(expectedGenres);

        // Act
        List<Genre> retrievedGenres = genreDAO.getAllGenres();

        // Assert
        assertEquals(expectedGenres, retrievedGenres);
    }
}
