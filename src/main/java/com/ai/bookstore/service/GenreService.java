package com.ai.bookstore.service;

import com.ai.bookstore.dao.GenreDAO;
import com.ai.bookstore.dto.GenreDTO;
import com.ai.bookstore.mapper.GenreMapper;
import com.ai.bookstore.model.Genre;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreService {

    private final GenreDAO genreDAO;
    private final GenreMapper genreMapper;

    public GenreService(GenreDAO genreDAO, GenreMapper genreMapper) {
        this.genreDAO = genreDAO;
        this.genreMapper = genreMapper;
    }

    @Transactional
    public GenreDTO createGenre(GenreDTO genreDTO) {
        Genre genre = genreMapper.dtoToGenre(genreDTO);
        genreDAO.saveOrUpdateGenre(genre);
        return genreMapper.genreToDto(genre);
    }

    @Transactional
    public GenreDTO updateGenre(Long genreId, GenreDTO genreDTO) {
        Genre existingGenre = genreDAO.getGenreById(genreId);
        if (existingGenre == null) {
            // Handle not found scenario
            return null;
        }

        // Update properties of the existing genre
        existingGenre.setName(genreDTO.getName());

        genreDAO.saveOrUpdateGenre(existingGenre);
        return genreMapper.genreToDto(existingGenre);
    }

    public GenreDTO getGenre(Long genreId) {
        Genre genre = genreDAO.getGenreById(genreId);
        if (genre == null) {
            // Handle not found scenario
            return null;
        }
        return genreMapper.genreToDto(genre);
    }

    @Transactional
    public List<GenreDTO> getAllGenres() {
        return genreDAO.getAllGenres().stream().map(genreMapper::genreToDto).collect(Collectors.toList());
    }
}
