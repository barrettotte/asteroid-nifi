package com.barrettotte.asteroid.repository;

import com.barrettotte.asteroid.model.Asteroid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsteroidRepository extends JpaRepository<Asteroid, String> {

}
