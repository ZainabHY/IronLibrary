package com.IronLibrary.HW3IronLibrary.repository;

import com.IronLibrary.HW3IronLibrary.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
