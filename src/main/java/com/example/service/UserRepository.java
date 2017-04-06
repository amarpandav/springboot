package com.example.service;

import com.example.domain.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by amarpandav on 03/04/17.
 */
public interface UserRepository extends JpaRepository<Reader, Long> {
    Reader findByUsername(String username);
}
