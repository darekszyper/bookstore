package com.ai.bookstore.service;

import com.ai.bookstore.dao.AuthorDAO;
import com.ai.bookstore.dto.AuthorDTO;
import com.ai.bookstore.mapper.AuthorMapper;
import com.ai.bookstore.model.Author;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AuthorServiceTest {

    @Mock
    private AuthorDAO authorDAO;

    @Mock
    private AuthorMapper authorMapper;

    @InjectMocks
    private AuthorService authorService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateAuthor() {
        // Arrange
        AuthorDTO authorDTO = new AuthorDTO();
        // Set properties in authorDTO

        Author author = new Author();
        // Set properties in author

        Mockito.when(authorMapper.dtoToAuthor(authorDTO)).thenReturn(author);
        Mockito.doNothing().when(authorDAO).saveOrUpdateAuthor(author);
        Mockito.when(authorMapper.authorToDto(author)).thenReturn(authorDTO);

        // Act
        AuthorDTO createdAuthor = authorService.createAuthor(authorDTO);

        // Assert
        assertEquals(authorDTO, createdAuthor);
    }

    @Test
    public void testUpdateAuthor() {
        // Arrange
        Long authorId = 1L;

        AuthorDTO authorDTO = new AuthorDTO();
        // Set properties in authorDTO

        Author existingAuthor = new Author();
        // Set properties in existingAuthor

        Mockito.when(authorDAO.getAuthorById(authorId)).thenReturn(existingAuthor);
        Mockito.when(authorMapper.dtoToAuthor(authorDTO)).thenReturn(existingAuthor);
        Mockito.doNothing().when(authorDAO).saveOrUpdateAuthor(existingAuthor);
        Mockito.when(authorMapper.authorToDto(existingAuthor)).thenReturn(authorDTO);

        // Act
        AuthorDTO updatedAuthor = authorService.updateAuthor(authorId, authorDTO);

        // Assert
        assertEquals(authorDTO, updatedAuthor);
    }

    @Test
    public void testGetAuthor() {
        // Arrange
        Long authorId = 1L;

        AuthorDTO authorDTO = new AuthorDTO();
        // Set properties in authorDTO

        Author existingAuthor = new Author();
        // Set properties in existingAuthor

        Mockito.when(authorDAO.getAuthorById(authorId)).thenReturn(existingAuthor);
        Mockito.when(authorMapper.authorToDto(existingAuthor)).thenReturn(authorDTO);

        // Act
        AuthorDTO retrievedAuthor = authorService.getAuthor(authorId);

        // Assert
        assertEquals(authorDTO, retrievedAuthor);
    }

    @Test
    public void testGetAuthorNotFound() {
        // Arrange
        Long authorId = 1L;

        Mockito.when(authorDAO.getAuthorById(authorId)).thenReturn(null);

        // Act
        AuthorDTO retrievedAuthor = authorService.getAuthor(authorId);

        // Assert
        assertNull(retrievedAuthor);
    }
}