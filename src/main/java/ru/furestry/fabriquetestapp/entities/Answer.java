package ru.furestry.fabriquetestapp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Answer {

    private Question question;

    private String text;
}
