package com.ai.bookstore.dao;

import com.ai.bookstore.model.Genre;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class GenreDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void saveOrUpdateGenre(Genre genre) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(genre);
    }

    public Genre getGenreById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Genre.class, id);
    }

    public List<Genre> getAllGenres() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Genre", Genre.class).list();
    }

    // You can add custom methods for genre-related operations here
}