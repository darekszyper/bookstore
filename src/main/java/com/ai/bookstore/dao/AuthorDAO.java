package com.ai.bookstore.dao;

import com.ai.bookstore.model.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AuthorDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void saveOrUpdateAuthor(Author author) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(author);
    }

    public Author getAuthorById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Author.class, id);
    }

    public List<Author> getAllAuthors() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Author", Author.class).list();
    }

    // You can add custom methods for author-related operations here
}
