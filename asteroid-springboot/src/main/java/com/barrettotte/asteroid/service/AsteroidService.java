package com.barrettotte.asteroid.service;

import com.barrettotte.asteroid.model.Asteroid;
import com.barrettotte.asteroid.repository.AsteroidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsteroidService {
    @Autowired
    AsteroidRepository asteroidRepository;

    public Optional<Asteroid> getById(String id) {
        return asteroidRepository.findById(id);
    }

    public List<Asteroid> getAll() {
        return asteroidRepository.findAll();
    }

    public Asteroid create(Asteroid asteroid) {
        Asteroid created = asteroidRepository.save(asteroid);
        // TODO: Kafka insert
        return created;
    }

    public Asteroid update(Asteroid toUpdate) {
        Asteroid updated = asteroidRepository.save(toUpdate);
        // TODO: Kafka insert
        return updated;
    }

    public String delete(String id) {
        asteroidRepository.deleteById(id);
        return id;
    }
}
