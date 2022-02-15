package ru.furestry.fabriquetestapp.entities.enums;

import ru.furestry.fabriquetestapp.entities.Question;

/**
 * Enum the types of {@link Question}
 *
 * @author Sevler
 */
public enum QuestionType {

    /**
     * Text Input Question
     */
    TEXT(),

    /**
     * One-choice question
     */
    ONE(),

    /**
     * Multiple-choice question
     */
    MULTI();

    private String[] answers;

    QuestionType(String... answers) {
        this.answers = answers;
    }
}
