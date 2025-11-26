package com.tintas.TintasShop.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 2000)
    private String description;

    private Double size;
    private String sizeUnit;
    private String naturalLight;
    private String orientation;
    private String dominantColors;
    private String preferredStyle;
    private String preferredPalette;

    @Column(length = 1000)
    private String constraints;

    private LocalDateTime createdAt;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getSize() { return size; }
    public void setSize(Double size) { this.size = size; }

    public String getSizeUnit() { return sizeUnit; }
    public void setSizeUnit(String sizeUnit) { this.sizeUnit = sizeUnit; }

    public String getNaturalLight() { return naturalLight; }
    public void setNaturalLight(String naturalLight) { this.naturalLight = naturalLight; }

    public String getOrientation() { return orientation; }
    public void setOrientation(String orientation) { this.orientation = orientation; }

    public String getDominantColors() { return dominantColors; }
    public void setDominantColors(String dominantColors) { this.dominantColors = dominantColors; }

    public String getPreferredStyle() { return preferredStyle; }
    public void setPreferredStyle(String preferredStyle) { this.preferredStyle = preferredStyle; }

    public String getPreferredPalette() { return preferredPalette; }
    public void setPreferredPalette(String preferredPalette) { this.preferredPalette = preferredPalette; }

    public String getConstraints() { return constraints; }
    public void setConstraints(String constraints) { this.constraints = constraints; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
