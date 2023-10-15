package com.ai.bookstore.controller;

import com.ai.bookstore.api.response.GenreResponse;
import com.ai.bookstore.dto.GenreDTO;
import com.ai.bookstore.mapper.GenreMapper;
import com.ai.bookstore.service.GenreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    private final GenreService genreService;
    private final GenreMapper genreMapper;

    public GenreController(GenreService genreService, GenreMapper genreMapper) {
        this.genreService = genreService;
        this.genreMapper = genreMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreResponse> getGenre(@PathVariable Long id) {
        GenreDTO genreDTO = genreService.getGenre(id);
        if (genreDTO != null) {
            GenreResponse genreResponse = genreMapper.dtoToResponse(genreDTO);
            return ResponseEntity.ok(genreResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<GenreResponse>> getAllGenres() {
        List<GenreDTO> genreDTOList = genreService.getAllGenres();
        List<GenreResponse> genreResponses = genreDTOList.stream()
                .map(genreMapper::dtoToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(genreResponses);
    }
}
