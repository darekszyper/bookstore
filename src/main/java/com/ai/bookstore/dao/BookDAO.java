package com.ai.bookstore.dao;

import com.ai.bookstore.model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class BookDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void saveOrUpdateBook(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(book);
    }

    public Book getBookById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Book.class, id);
    }

    public List<Book> getAllBooks() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Book", Book.class).list();
    }

    // Custom method to search books by title, author, or genre
    @Transactional
    public List<Book> searchBooks(String keyword) {
        Session session = sessionFactory.getCurrentSession();
        String lowercaseKeyword = keyword.toLowerCase().replaceAll("\\s", ""); // Remove spaces from the keyword
        String query = "FROM Book b WHERE lower(replace(b.title, ' ', '')) LIKE :keyword " +
                "OR lower(replace(b.author.name, ' ', '')) LIKE :keyword " +
                "OR lower(replace(b.genre.name, ' ', '')) LIKE :keyword";
        return session.createQuery(query, Book.class)
                .setParameter("keyword", "%" + lowercaseKeyword + "%")
                .list();
    }

    @Transactional
    public void deleteBook(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(book);
    }
}
