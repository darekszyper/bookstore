package com.ai.bookstore.mapper;

import com.ai.bookstore.api.request.AuthorRequest;
import com.ai.bookstore.api.response.AuthorResponse;
import com.ai.bookstore.dto.AuthorDTO;
import com.ai.bookstore.model.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public AuthorDTO authorToDto(Author author) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(author.getId());
        authorDTO.setName(author.getName());
        return authorDTO;
    }

    public Author dtoToAuthor(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setId(authorDTO.getId());
        author.setName(authorDTO.getName());
        return author;
    }

    public AuthorDTO requestToDto(AuthorRequest request) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName(request.getName());
        return authorDTO;
    }

    public AuthorResponse dtoToResponse(AuthorDTO authorDTO) {
        AuthorResponse response = new AuthorResponse();
        response.setId(authorDTO.getId());
        response.setName(authorDTO.getName());
        return response;
    }
}