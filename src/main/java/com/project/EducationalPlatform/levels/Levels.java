package com.project.EducationalPlatform.levels;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Levels {
    private Long id;
    private int level;
    private String name;

    public Levels() {
    }
    public Levels(Long id, int level, String name){
        super();
        this.id = id;
        this.level=level;
        this.name = name;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
