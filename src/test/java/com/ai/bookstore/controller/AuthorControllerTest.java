package com.ai.bookstore.controller;

import com.ai.bookstore.api.response.AuthorResponse;
import com.ai.bookstore.dto.AuthorDTO;
import com.ai.bookstore.mapper.AuthorMapper;
import com.ai.bookstore.service.AuthorService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AuthorControllerTest {

    @Mock
    private AuthorService authorService;

    @Mock
    private AuthorMapper authorMapper;

    @InjectMocks
    private AuthorController authorController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAuthor() {
        // Arrange
        Long authorId = 1L;
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(authorId);
        authorDTO.setName("Test Author");

        AuthorResponse expectedResponse = new AuthorResponse();
        expectedResponse.setId(authorId);
        expectedResponse.setName("Test Author");

        Mockito.when(authorService.getAuthor(authorId)).thenReturn(authorDTO);
        Mockito.when(authorMapper.dtoToResponse(authorDTO)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<AuthorResponse> response = authorController.getAuthor(authorId);

        // Assert
        assertEquals(expectedResponse, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testGetAuthorNotFound() {
        // Arrange
        Long authorId = 1L;
        Mockito.when(authorService.getAuthor(authorId)).thenReturn(null);

        // Act
        ResponseEntity<AuthorResponse> response = authorController.getAuthor(authorId);

        // Assert
        assertTrue(response.getStatusCode().is4xxClientError());
    }

    @Test
    public void testGetAllAuthors() {
        // Arrange
        List<AuthorDTO> authorDTOList = new ArrayList<>();
        AuthorDTO author1 = new AuthorDTO();
        author1.setId(1L);
        author1.setName("Author 1");
        AuthorDTO author2 = new AuthorDTO();
        author2.setId(2L);
        author2.setName("Author 2");
        authorDTOList.add(author1);
        authorDTOList.add(author2);

        AuthorResponse authorResponse1 = new AuthorResponse();
        authorResponse1.setId(1L);
        authorResponse1.setName("Author 1");
        AuthorResponse authorResponse2 = new AuthorResponse();
        authorResponse2.setId(2L);
        authorResponse2.setName("Author 2");

        List<AuthorResponse> expectedResponses = new ArrayList<>();
        expectedResponses.add(authorResponse1);
        expectedResponses.add(authorResponse2);

        Mockito.when(authorService.getAllAuthors()).thenReturn(authorDTOList);
        Mockito.when(authorMapper.dtoToResponse(author1)).thenReturn(authorResponse1);
        Mockito.when(authorMapper.dtoToResponse(author2)).thenReturn(authorResponse2);

        // Act
        ResponseEntity<List<AuthorResponse>> response = authorController.getAllAuthors();

        // Assert
        assertEquals(expectedResponses, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }
}