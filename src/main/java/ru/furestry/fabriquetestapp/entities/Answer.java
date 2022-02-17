package ru.furestry.fabriquetestapp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Class representing a answer
 *
 * @author Sevler
 */
@Data
@AllArgsConstructor
public class Answer {

    private Question question;

    private String text;
}
