package ru.furestry.fabriquetestapp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;

/**
 * Class representing a survey
 *
 * @author Sevler
 */
@Data
@AllArgsConstructor
public class Survey {

    private long id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String description;

    private HashSet<Question> questions;
}
