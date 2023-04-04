package com.barrettotte.asteroid.service;

import com.barrettotte.asteroid.model.Asteroid;
import com.barrettotte.asteroid.repository.AsteroidRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsteroidService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsteroidService.class);

    @Autowired
    AsteroidRepository asteroidRepository;

    public Optional<Asteroid> getById(String id) {
        LOGGER.debug("Get asteroid {}", id);
        return asteroidRepository.findById(id);
    }

    public List<Asteroid> getAll() {
        LOGGER.debug("Get all");
        return asteroidRepository.findAll();
    }

    public Asteroid create(Asteroid asteroid) {
        Asteroid created = asteroidRepository.save(asteroid);
        LOGGER.debug("Creating asteroid {}", asteroid);
        return created;
    }

    public Asteroid update(Asteroid toUpdate) {
        Asteroid updated = asteroidRepository.save(toUpdate);
        LOGGER.debug("Updating asteroid {}", toUpdate);
        return updated;
    }

    public String delete(String id) {
        asteroidRepository.deleteById(id);
        LOGGER.debug("Updating asteroid {}", id);
        return id;
    }
}
