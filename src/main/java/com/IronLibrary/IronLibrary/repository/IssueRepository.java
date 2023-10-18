package com.IronLibrary.IronLibrary.repository;

import com.IronLibrary.IronLibrary.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import com.IronLibrary.IronLibrary.entity.Issue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Integer> {

    @Query(value="SELECT * FROM issue i JOIN student s ON s.usn=i.student_id WHERE s.usn =:studentUsn", nativeQuery=true)
    List<Issue> findAllByStudentUsn(@Param("studentUsn") String studentUsn);

//    Optional<Book> findByReturnDate();
}
