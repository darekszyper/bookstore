package com.ai.bookstore.controller;

import com.ai.bookstore.api.response.GenreResponse;
import com.ai.bookstore.dto.GenreDTO;
import com.ai.bookstore.mapper.GenreMapper;
import com.ai.bookstore.service.GenreService;
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

public class GenreControllerTest {

    @Mock
    private GenreService genreService;

    @Mock
    private GenreMapper genreMapper;

    @InjectMocks
    private GenreController genreController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetGenre() {
        // Arrange
        Long genreId = 1L;
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setId(genreId);
        genreDTO.setName("Test Genre");
        GenreResponse expectedResponse = new GenreResponse();
        expectedResponse.setId(genreId);
        expectedResponse.setName("Test Genre");

        Mockito.when(genreService.getGenre(genreId)).thenReturn(genreDTO);
        Mockito.when(genreMapper.dtoToResponse(genreDTO)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<GenreResponse> response = genreController.getGenre(genreId);

        // Assert
        assertEquals(expectedResponse, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testGetGenreNotFound() {
        // Arrange
        Long genreId = 1L;
        Mockito.when(genreService.getGenre(genreId)).thenReturn(null);

        // Act
        ResponseEntity<GenreResponse> response = genreController.getGenre(genreId);

        // Assert
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testGetAllGenres() {
        // Arrange
        List<GenreDTO> genreDTOList = new ArrayList<>();
        GenreDTO genre1 = new GenreDTO();
        genre1.setId(1L);
        genre1.setName("Genre 1");
        GenreDTO genre2 = new GenreDTO();
        genre2.setId(2L);
        genre2.setName("Genre 2");
        genreDTOList.add(genre1);
        genreDTOList.add(genre2);

        GenreResponse genreResponse1 = new GenreResponse();
        genreResponse1.setId(1L);
        genreResponse1.setName("Genre 1");
        GenreResponse genreResponse2 = new GenreResponse();
        genreResponse2.setId(2L);
        genreResponse2.setName("Genre 2");

        List<GenreResponse> expectedResponses = new ArrayList<>();
        expectedResponses.add(genreResponse1);
        expectedResponses.add(genreResponse2);

        Mockito.when(genreService.getAllGenres()).thenReturn(genreDTOList);
        Mockito.when(genreMapper.dtoToResponse(genre1)).thenReturn(genreResponse1);
        Mockito.when(genreMapper.dtoToResponse(genre2)).thenReturn(genreResponse2);

        // Act
        ResponseEntity<List<GenreResponse>> response = genreController.getAllGenres();

        // Assert
        assertEquals(expectedResponses, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }
}