package com.ai.bookstore;

import com.ai.bookstore.dao.AuthorDAO;
import com.ai.bookstore.dao.BookDAO;
import com.ai.bookstore.dao.GenreDAO;
import com.ai.bookstore.util.DataPopulator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@ComponentScan("com.ai.bookstore")
@Import({com.ai.bookstore.config.HibernateConfig.class, com.ai.bookstore.config.DatabaseConfig.class})
public class BookstoreApplication {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BookstoreApplication.class);

        BookDAO bookDAO = context.getBean(BookDAO.class);
        AuthorDAO authorDAO = context.getBean(AuthorDAO.class);
        GenreDAO genreDAO = context.getBean(GenreDAO.class);

        //Populate Database with random data
        DataPopulator dataPopulator = new DataPopulator(authorDAO, genreDAO, bookDAO);
        dataPopulator.populateData();

        context.close();
    }
}