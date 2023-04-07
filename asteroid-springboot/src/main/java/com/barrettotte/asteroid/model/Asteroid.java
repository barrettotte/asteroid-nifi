package com.barrettotte.asteroid.model;

import java.time.Instant;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "asteroid")
public class Asteroid {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "diameter_min")
    private Float diameterMin;

    @Column(name = "diameter_max")
    private Float diameterMax;

    @Column(name = "hazard")
    private Boolean hazard;

    @Column(name = "relative_velocity")
    private Float relativeVelocity;

    @Column(name = "distance")
    private Float distance;

    @Column(name = "orbiting_body")
    private String orbitingBody;

    @Column(name = "created")
    private Instant created;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated")
    private Instant updated;

    @Column(name = "updated_by")
    private String updatedBy;

    public Asteroid() {
        // nop
    }

    public Asteroid(String id, String name, Float diameterMin, Float diameterMax, Boolean hazard,
                    Float relativeVelocity, Float distance, String orbitingBody, Instant created,
                    String createdBy, Instant updated, String updatedBy) {
        this.id = id;
        this.name = name;
        this.diameterMin = diameterMin;
        this.diameterMax = diameterMax;
        this.hazard = hazard;
        this.relativeVelocity = relativeVelocity;
        this.distance = distance;
        this.orbitingBody = orbitingBody;
        this.created = created;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getDiameterMin() {
        return diameterMin;
    }

    public void setDiameterMin(Float diameterMin) {
        this.diameterMin = diameterMin;
    }

    public Float getDiameterMax() {
        return diameterMax;
    }

    public void setDiameterMax(Float diameterMax) {
        this.diameterMax = diameterMax;
    }

    public Boolean isHazard() {
        return hazard;
    }

    public Boolean getHazard() {
        return hazard;
    }

    public void setHazard(Boolean hazard) {
        this.hazard = hazard;
    }

    public Float getRelativeVelocity() {
        return relativeVelocity;
    }

    public void setRelativeVelocity(Float relativeVelocity) {
        this.relativeVelocity = relativeVelocity;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public String getOrbitingBody() {
        return orbitingBody;
    }

    public void setOrbitingBody(String orbitingBody) {
        this.orbitingBody = orbitingBody;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getUpdated() {
        return updated;
    }

    public void setUpdated(Instant updated) {
        this.updated = updated;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public boolean equals (Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Asteroid other)) {
            return false;
        }
        return (Objects.equals(id, other.id) &&
                Objects.equals(name, other.name) &&
                Objects.equals(diameterMin, other.diameterMax) &&
                Objects.equals(diameterMax, other.diameterMax) &&
                Objects.equals(hazard, other.hazard) &&
                Objects.equals(relativeVelocity, other.relativeVelocity) &&
                Objects.equals(distance, other.distance) &&
                Objects.equals(orbitingBody, other.orbitingBody) &&
                Objects.equals(created, other.created) &&
                Objects.equals(createdBy, other.createdBy) &&
                Objects.equals(updated, other.updated) &&
                Objects.equals(updatedBy, other.updatedBy)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, diameterMin, diameterMax, hazard, relativeVelocity, distance, orbitingBody,
                created, createdBy, updated, updatedBy);
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
                "orbitingBody=" + orbitingBody + "," +
                "created=" + created + "," +
                "createdBy='" + createdBy + "'," +
                "updated=" + updated + "," +
                "updatedBy='" + updatedBy + "'" +
                "}";
    }
}
