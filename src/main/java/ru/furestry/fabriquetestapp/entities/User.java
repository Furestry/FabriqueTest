package ru.furestry.fabriquetestapp.entities;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Class representing a user
 *
 * @author Sevler
 */
@Data
public class User {

    private long id;

    private Map<Survey, List<Answer>> answers;

    public User(long id) {
        this.id = id;
    }
}
