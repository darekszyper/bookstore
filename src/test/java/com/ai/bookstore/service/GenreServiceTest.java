package com.ai.bookstore.service;

import com.ai.bookstore.dao.GenreDAO;
import com.ai.bookstore.dto.GenreDTO;
import com.ai.bookstore.mapper.GenreMapper;
import com.ai.bookstore.model.Genre;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GenreServiceTest {

    @Mock
    private GenreDAO genreDAO;

    @Mock
    private GenreMapper genreMapper;

    @InjectMocks
    private GenreService genreService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateGenre() {
        // Arrange
        GenreDTO genreDTO = new GenreDTO();
        // Set properties in genreDTO

        Genre genre = new Genre();
        // Set properties in genre

        Mockito.when(genreMapper.dtoToGenre(genreDTO)).thenReturn(genre);
        Mockito.doNothing().when(genreDAO).saveOrUpdateGenre(genre);
        Mockito.when(genreMapper.genreToDto(genre)).thenReturn(genreDTO);

        // Act
        GenreDTO createdGenre = genreService.createGenre(genreDTO);

        // Assert
        assertEquals(genreDTO, createdGenre);
    }

    @Test
    public void testUpdateGenre() {
        // Arrange
        Long genreId = 1L;

        GenreDTO genreDTO = new GenreDTO();
        // Set properties in genreDTO

        Genre existingGenre = new Genre();
        // Set properties in existingGenre

        Mockito.when(genreDAO.getGenreById(genreId)).thenReturn(existingGenre);
        Mockito.when(genreMapper.dtoToGenre(genreDTO)).thenReturn(existingGenre);
        Mockito.doNothing().when(genreDAO).saveOrUpdateGenre(existingGenre);
        Mockito.when(genreMapper.genreToDto(existingGenre)).thenReturn(genreDTO);

        // Act
        GenreDTO updatedGenre = genreService.updateGenre(genreId, genreDTO);

        // Assert
        assertEquals(genreDTO, updatedGenre);
    }

    @Test
    public void testGetGenre() {
        // Arrange
        Long genreId = 1L;

        GenreDTO genreDTO = new GenreDTO();
        // Set properties in genreDTO

        Genre existingGenre = new Genre();
        // Set properties in existingGenre

        Mockito.when(genreDAO.getGenreById(genreId)).thenReturn(existingGenre);
        Mockito.when(genreMapper.genreToDto(existingGenre)).thenReturn(genreDTO);

        // Act
        GenreDTO retrievedGenre = genreService.getGenre(genreId);

        // Assert
        assertEquals(genreDTO, retrievedGenre);
    }

    @Test
    public void testGetGenreNotFound() {
        // Arrange
        Long genreId = 1L;

        Mockito.when(genreDAO.getGenreById(genreId)).thenReturn(null);

        // Act
        GenreDTO retrievedGenre = genreService.getGenre(genreId);

        // Assert
        assertNull(retrievedGenre);
    }
}