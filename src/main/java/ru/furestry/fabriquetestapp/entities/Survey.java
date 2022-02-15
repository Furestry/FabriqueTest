package ru.furestry.fabriquetestapp.entities;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Class representing a survey
 *
 * @author Sevler
 */
@Data
public class Survey {

    private long id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String description;

    private List<Question> questions;
}
