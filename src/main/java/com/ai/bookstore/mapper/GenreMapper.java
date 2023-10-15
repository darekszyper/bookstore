package com.ai.bookstore.mapper;

import com.ai.bookstore.api.request.GenreRequest;
import com.ai.bookstore.api.response.GenreResponse;
import com.ai.bookstore.dto.GenreDTO;
import com.ai.bookstore.model.Genre;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper {

    public GenreDTO genreToDto(Genre genre) {
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setId(genre.getId());
        genreDTO.setName(genre.getName());
        return genreDTO;
    }

    public Genre dtoToGenre(GenreDTO genreDTO) {
        Genre genre = new Genre();
        genre.setId(genreDTO.getId());
        genre.setName(genreDTO.getName());
        return genre;
    }

    public GenreDTO requestToDto(GenreRequest request) {
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setName(request.getName());
        return genreDTO;
    }

    public GenreResponse dtoToResponse(GenreDTO genreDTO) {
        GenreResponse response = new GenreResponse();
        response.setId(genreDTO.getId());
        response.setName(genreDTO.getName());
        return response;
    }
}