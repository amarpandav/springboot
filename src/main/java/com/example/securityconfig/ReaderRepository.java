package com.example.securityconfig;

import com.example.domain.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * service for reader
 */
public interface ReaderRepository
        extends JpaRepository<Reader, String> {
}
