package com.barrettotte.asteroid.model;

import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "asteroid")
public class Asteroid {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "diameter_min")
    private float diameterMin;

    @Column(name = "diameter_max")
    private float diameterMax;

    @Column(name = "hazard")
    private boolean hazard;

    @Column(name = "relative_velocity")
    private float relativeVelocity;

    @Column(name = "distance")
    private float distance;

    @Column(name = "orbiting_body")
    private String orbitingBody;

    public Asteroid() {
        // nop
    }

    public Asteroid(String id, String name, float diameterMin, float diameterMax, boolean hazard,
                    float relativeVelocity, float distance, String orbitingBody) {
        this.id = id;
        this.name = name;
        this.diameterMin = diameterMin;
        this.diameterMax = diameterMax;
        this.hazard = hazard;
        this.relativeVelocity = relativeVelocity;
        this.distance = distance;
        this.orbitingBody = orbitingBody;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getDiameterMin() {
        return diameterMin;
    }

    public void setDiameterMin(float diameterMin) {
        this.diameterMin = diameterMin;
    }

    public float getDiameterMax() {
        return diameterMax;
    }

    public void setDiameterMax(float diameterMax) {
        this.diameterMax = diameterMax;
    }

    public boolean getHazard() {
        return hazard;
    }

    public void setHazard(boolean hazard) {
        this.hazard = hazard;
    }

    public float getRelativeVelocity() {
        return relativeVelocity;
    }

    public void setRelativeVelocity(float relativeVelocity) {
        this.relativeVelocity = relativeVelocity;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public String getOrbitingBody() {
        return orbitingBody;
    }

    public void setOrbitingBody(String orbitingBody) {
        this.orbitingBody = orbitingBody;
    }

    @Override
    public boolean equals (Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Asteroid other)) {
            return false;
        }
        return (Objects.equals(this.id, other.id) &&
                Objects.equals(this.name, other.name) &&
                this.diameterMin == other.diameterMin &&
                this.diameterMax == other.diameterMax &&
                this.hazard == other.hazard &&
                this.relativeVelocity == other.relativeVelocity &&
                this.distance == other.distance &&
                Objects.equals(this.orbitingBody, other.orbitingBody));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, diameterMin, diameterMax, hazard, relativeVelocity, distance, orbitingBody);
    }

    @Override
    public String toString() {
        return "Asteroid{" +
                "id='" + id + "'," +
                "name='" + name + "'," +
                "diameterMin=" + diameterMin + "," +
                "diameterMax=" + diameterMax + "," +
                "hazard=" + hazard + "," +
                "relativeVelocity=" + relativeVelocity + "," +
                "distance=" + distance + "," +
                "orbitingBody=" + orbitingBody +
                "}";
    }
}
