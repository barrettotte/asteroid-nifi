package com.barrettotte.asteroid.repository;

import com.barrettotte.asteroid.model.Asteroid;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsteroidRepository { // extends CrudRepository<Asteroid, Long> {

    Asteroid getById(long id);

    List<Asteroid> getAll();

    Asteroid create(); // TODO:

    Asteroid update(); // TODO:

    long delete(); // TODO:
}
