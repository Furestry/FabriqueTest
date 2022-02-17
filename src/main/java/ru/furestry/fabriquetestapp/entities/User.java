package ru.furestry.fabriquetestapp.entities;

import lombok.Data;

import java.util.HashSet;
import java.util.Map;

/**
 * Class representing a user
 *
 * @author Sevler
 */
@Data
public class User {

    private long id;

    private Map<Survey, HashSet<Answer>> answers;

    /**
     * Class constructor
     *
     * @param id User id
     */
    public User(long id) {
        this.id = id;
    }
}
