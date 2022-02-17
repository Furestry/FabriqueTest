package ru.furestry.fabriquetestapp.entities;

import lombok.Data;

import java.util.List;

/**
 * Class representing a user
 *
 * @author Sevler
 */
@Data
public class User {

    private long id;

    private List<Survey> surveys;

    public User(long id) {
        this.id = id;
    }
}
