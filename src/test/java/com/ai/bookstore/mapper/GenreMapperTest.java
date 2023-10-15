package com.ai.bookstore.mapper;

import com.ai.bookstore.api.request.GenreRequest;
import com.ai.bookstore.api.response.GenreResponse;
import com.ai.bookstore.dto.GenreDTO;
import com.ai.bookstore.model.Genre;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GenreMapperTest {

    private final GenreMapper genreMapper = new GenreMapper();

    @Test
    public void testGenreToDto() {
        Genre genre = new Genre();
        genre.setId(1L);
        genre.setName("Fiction");

        GenreDTO genreDTO = genreMapper.genreToDto(genre);

        assertEquals(genre.getId(), genreDTO.getId());
        assertEquals(genre.getName(), genreDTO.getName());
    }

    @Test
    public void testDtoToGenre() {
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setId(1L);
        genreDTO.setName("Mystery");

        Genre genre = genreMapper.dtoToGenre(genreDTO);

        assertEquals(genreDTO.getId(), genre.getId());
        assertEquals(genreDTO.getName(), genre.getName());
    }

    @Test
    public void testRequestToDto() {
        GenreRequest request = new GenreRequest();
        request.setName("Science Fiction");

        GenreDTO genreDTO = genreMapper.requestToDto(request);

        assertEquals(request.getName(), genreDTO.getName());
    }

    @Test
    public void testDtoToResponse() {
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setId(1L);
        genreDTO.setName("Romance");

        GenreResponse response = genreMapper.dtoToResponse(genreDTO);

        assertEquals(genreDTO.getId(), response.getId());
        assertEquals(genreDTO.getName(), response.getName());
    }
}