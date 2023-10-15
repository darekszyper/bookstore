package com.ai.bookstore.util;

import com.ai.bookstore.dao.AuthorDAO;
import com.ai.bookstore.dao.GenreDAO;
import com.ai.bookstore.dao.BookDAO;
import com.ai.bookstore.model.Author;
import com.ai.bookstore.model.Genre;
import com.ai.bookstore.model.Book;

import java.util.Random;

public class DataPopulator {

    private final AuthorDAO authorDAO;
    private final GenreDAO genreDAO;
    private final BookDAO bookDAO;

    public DataPopulator(AuthorDAO authorDAO, GenreDAO genreDAO, BookDAO bookDAO) {
        this.authorDAO = authorDAO;
        this.genreDAO = genreDAO;
        this.bookDAO = bookDAO;
    }

    public void populateData() {
        // Populate Authors
        Author author1 = new Author();
        author1.setName("Author 1");
        authorDAO.saveOrUpdateAuthor(author1);

        Author author2 = new Author();
        author2.setName("Author 2");
        authorDAO.saveOrUpdateAuthor(author2);

        // Populate Genres
        Genre genre1 = new Genre();
        genre1.setName("Genre 1");
        genreDAO.saveOrUpdateGenre(genre1);

        Genre genre2 = new Genre();
        genre2.setName("Genre 2");
        genreDAO.saveOrUpdateGenre(genre2);

        // Generate and populate random books
        Random random = new Random();
        for (int i = 1; i <= 10; i++) {
            Book book = new Book();
            book.setTitle("Book " + i);
            book.setPrice(10 + random.nextInt(40));  // Random price between 10 and 49
            book.setQuantityAvailable(10 + random.nextInt(41));  // Random quantity between 10 and 50
            book.setAuthor(i % 2 == 0 ? author1 : author2);  // Assign authors alternately
            book.setGenre(i % 2 == 0 ? genre1 : genre2);    // Assign genres alternately
            bookDAO.saveOrUpdateBook(book);
        }
    }
}