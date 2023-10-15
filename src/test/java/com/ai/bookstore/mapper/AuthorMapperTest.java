package com.ai.bookstore.mapper;

import com.ai.bookstore.api.request.AuthorRequest;
import com.ai.bookstore.api.response.AuthorResponse;
import com.ai.bookstore.dto.AuthorDTO;
import com.ai.bookstore.model.Author;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AuthorMapperTest {

    private final AuthorMapper authorMapper = new AuthorMapper();

    @Test
    public void testAuthorToDto() {
        Author author = new Author();
        author.setId(1L);
        author.setName("John Doe");

        AuthorDTO authorDTO = authorMapper.authorToDto(author);

        assertEquals(author.getId(), authorDTO.getId());
        assertEquals(author.getName(), authorDTO.getName());
    }

    @Test
    public void testDtoToAuthor() {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(1L);
        authorDTO.setName("Jane Smith");

        Author author = authorMapper.dtoToAuthor(authorDTO);

        assertEquals(authorDTO.getId(), author.getId());
        assertEquals(authorDTO.getName(), author.getName());
    }

    @Test
    public void testRequestToDto() {
        AuthorRequest request = new AuthorRequest();
        request.setName("Mark Johnson");

        AuthorDTO authorDTO = authorMapper.requestToDto(request);

        assertEquals(request.getName(), authorDTO.getName());
    }

    @Test
    public void testDtoToResponse() {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(1L);
        authorDTO.setName("Michael Brown");

        AuthorResponse response = authorMapper.dtoToResponse(authorDTO);

        assertEquals(authorDTO.getId(), response.getId());
        assertEquals(authorDTO.getName(), response.getName());
    }
}