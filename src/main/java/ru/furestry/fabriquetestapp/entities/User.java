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

    private long id = 1;

    private List<Survey> surveys;
}
