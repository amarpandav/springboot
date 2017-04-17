package com.example.testdata;

import com.example.domain.Book;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * test data creater service
 */
@Service
public class TestDataService {

    @PersistenceContext
    EntityManager em;

    @Transactional
    public void createTestData() {
        Book book = new Book();
        book.setReader("AMar");
        em.persist(book);

    }
}
