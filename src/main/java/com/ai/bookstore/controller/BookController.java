package com.ai.bookstore.controller;

import com.ai.bookstore.api.request.CreateBookRequest;
import com.ai.bookstore.api.request.UpdateBookRequest;
import com.ai.bookstore.api.response.BookResponse;
import com.ai.bookstore.dto.BookDTO;
import com.ai.bookstore.mapper.BookMapper;
import com.ai.bookstore.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    public BookController(BookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBook(@PathVariable Long id) {
        BookDTO bookDTO = bookService.getBook(id);
        if (bookDTO != null) {
            BookResponse bookResponse = bookMapper.dtoToResponse(bookDTO);
            return ResponseEntity.ok(bookResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        List<BookDTO> bookDTOList = bookService.getAllBooks();
        List<BookResponse> bookResponses = bookDTOList.stream()
                .map(bookMapper::dtoToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(bookResponses);
    }

    @PostMapping
    public ResponseEntity<BookResponse> createBook(@RequestBody CreateBookRequest request) {
        BookDTO bookDTO = bookMapper.requestToDto(request);
        BookDTO createdBook = bookService.createBook(bookDTO);
        BookResponse bookResponse = bookMapper.dtoToResponse(createdBook);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Long id, @RequestBody UpdateBookRequest request) {
        BookDTO bookDTO = bookMapper.requestToDto(request);
        BookDTO updatedBook = bookService.updateBook(id, bookDTO);
        if (updatedBook != null) {
            BookResponse bookResponse = bookMapper.dtoToResponse(updatedBook);
            return ResponseEntity.ok(bookResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (bookService.deleteBook(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public List<BookResponse> searchBooks(@RequestParam(name = "keyword") String keyword) {
        List<BookDTO> bookDTOs = bookService.searchBooks(keyword);
        return bookDTOs.stream().map(bookMapper::dtoToResponse).collect(Collectors.toList());
    }
}
