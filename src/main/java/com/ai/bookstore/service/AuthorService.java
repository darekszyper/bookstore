package com.ai.bookstore.service;

import com.ai.bookstore.dao.AuthorDAO;
import com.ai.bookstore.dto.AuthorDTO;
import com.ai.bookstore.mapper.AuthorMapper;
import com.ai.bookstore.model.Author;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final AuthorDAO authorDAO;
    private final AuthorMapper authorMapper;

    public AuthorService(AuthorDAO authorDAO, AuthorMapper authorMapper) {
        this.authorDAO = authorDAO;
        this.authorMapper = authorMapper;
    }

    @Transactional
    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        Author author = authorMapper.dtoToAuthor(authorDTO);
        authorDAO.saveOrUpdateAuthor(author);
        return authorMapper.authorToDto(author);
    }

    @Transactional
    public AuthorDTO updateAuthor(Long authorId, AuthorDTO authorDTO) {
        Author existingAuthor = authorDAO.getAuthorById(authorId);
        if (existingAuthor == null) {
            // Handle not found scenario
            return null;
        }

        // Update properties of the existing author
        existingAuthor.setName(authorDTO.getName());

        authorDAO.saveOrUpdateAuthor(existingAuthor);
        return authorMapper.authorToDto(existingAuthor);
    }

    public AuthorDTO getAuthor(Long authorId) {
        Author author = authorDAO.getAuthorById(authorId);
        if (author == null) {
            // Handle not found scenario
            return null;
        }
        return authorMapper.authorToDto(author);
    }

    @Transactional
    public List<AuthorDTO> getAllAuthors() {
        return authorDAO.getAllAuthors().stream().map(authorMapper::authorToDto).collect(Collectors.toList());
    }
}
