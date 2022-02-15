package ru.furestry.fabriquetestapp.entities;

import lombok.Data;
import ru.furestry.fabriquetestapp.entities.enums.QuestionType;

/**
 * Class representing a question
 *
 * @author Sevler
 */
@Data
public class Question {

    private long id;

    private String text;

    private QuestionType type;
}
