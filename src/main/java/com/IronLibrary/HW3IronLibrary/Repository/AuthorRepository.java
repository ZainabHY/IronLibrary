package com.IronLibrary.HW3IronLibrary.Repository;

import com.IronLibrary.HW3IronLibrary.Entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;



@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Optional<Author> findByEmail(String email);

    Optional<Author> findByName(String authorName);
}