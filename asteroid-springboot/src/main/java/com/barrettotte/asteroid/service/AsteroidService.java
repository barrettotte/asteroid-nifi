package com.barrettotte.asteroid.service;

import com.barrettotte.asteroid.model.Asteroid;
import com.barrettotte.asteroid.repository.AsteroidRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class AsteroidService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsteroidService.class);
    private final String appName = "asteroid-sb";


    @Autowired
    AsteroidRepository asteroidRepository;

    public Optional<Asteroid> getById(String id) {
        LOGGER.debug("Fetched asteroid {}", id);
        return asteroidRepository.findById(id);
    }

    public List<Asteroid> getAll() {
        LOGGER.debug("Fetched all asteroids");
        return asteroidRepository.findAll();
    }

    public Asteroid create(Asteroid asteroid) {
        // set audit fields
        asteroid.setCreatedBy(appName);
        asteroid.setCreated(Instant.now());
        asteroid.setUpdatedBy(appName);
        asteroid.setUpdated(Instant.now());

        Asteroid created = asteroidRepository.save(asteroid);
        LOGGER.debug("Created asteroid {}", created);
        return created;
    }

    public Asteroid update(Asteroid original, Asteroid toUpdate) {
        Asteroid merged = merge(original, toUpdate);
        Asteroid updated = asteroidRepository.save(merged);
        LOGGER.debug("Updated asteroid => original={}, updated={}", original, updated);
        return updated;
    }

    public String delete(String id) {
        asteroidRepository.deleteById(id);
        LOGGER.debug("Deleted asteroid {}", id);
        return id;
    }

    // merge original asteroid and updated asteroid
    private Asteroid merge(Asteroid original, Asteroid update) {
        return new Asteroid(
                original.getId(),
                Optional.ofNullable(update.getName()).orElse(original.getName()),
                Optional.ofNullable(update.getDiameterMin()).orElse(original.getDiameterMin()),
                Optional.ofNullable(update.getDiameterMax()).orElse(original.getDiameterMax()),
                Optional.ofNullable(update.getHazard()).orElse(original.getHazard()),
                Optional.ofNullable(update.getRelativeVelocity()).orElse(original.getRelativeVelocity()),
                Optional.ofNullable(update.getDistance()).orElse(original.getDistance()),
                Optional.ofNullable(update.getOrbitingBody()).orElse(original.getOrbitingBody()),
                original.getCreated(),
                original.getCreatedBy(),
                Instant.now(),
                appName
        );
    }
}
