package com.IronLibrary.IronLibrary;

import com.IronLibrary.IronLibrary.entity.LibraryHelperMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;


@SpringBootApplication
public class IronLibraryApplication implements CommandLineRunner {
	@Autowired
	LibraryHelperMethods libraryHelperMethods;

	public static void main(String[] args){
		SpringApplication.run(IronLibraryApplication.class, args);

	}

	@Override
	public void run(String... args){
		libraryHelperMethods.greeting();
		libraryHelperMethods.userInteraction();
	}
}
