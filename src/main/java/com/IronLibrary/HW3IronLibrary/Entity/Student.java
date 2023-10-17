package com.IronLibrary.HW3IronLibrary.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Student {
    @Id
    private String usn;
    private String name;

    public Student() {
    }

    public Student(String usn, String name) {
        this.usn = usn;
        this.name = name;
    }

    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "usn='" + usn + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
    @OneToMany(mappedBy = "student")
    private List<Book> issuedBooks;

}
