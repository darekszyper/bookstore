package com.ai.bookstore.controller;

import com.ai.bookstore.api.response.AuthorResponse;
import com.ai.bookstore.dto.AuthorDTO;
import com.ai.bookstore.mapper.AuthorMapper;
import com.ai.bookstore.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;
    private final AuthorMapper authorMapper;

    public AuthorController(AuthorService authorService, AuthorMapper authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> getAuthor(@PathVariable Long id) {
        AuthorDTO authorDTO = authorService.getAuthor(id);
        if (authorDTO != null) {
            AuthorResponse authorResponse = authorMapper.dtoToResponse(authorDTO);
            return ResponseEntity.ok(authorResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponse>> getAllAuthors() {
        List<AuthorDTO> authorDTOList = authorService.getAllAuthors();
        List<AuthorResponse> authorResponses = authorDTOList.stream()
                .map(authorMapper::dtoToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(authorResponses);
    }
}
