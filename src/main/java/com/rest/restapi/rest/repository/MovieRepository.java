package com.rest.restapi.rest.repository;

//This is the data access layer.
//SprigData JPI handles all the implementation for us by simply extending JpaRepository


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.restapi.rest.model.Movie;

@Repository

//JpaRepository<Entity,IdDataType> provieds CRUD methods like save,findAll,findById,delete etc.


public interface  MovieRepository extends JpaRepository<Movie, Long> {

    //Spring-data-jpa will automatically implement methods like:
    // List<Movie> findByDirector(String director);
    //We can define custom queries if needed.
}