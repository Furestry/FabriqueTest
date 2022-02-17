package ru.furestry.fabriquetestapp.services;

import org.springframework.stereotype.Service;
import ru.furestry.fabriquetestapp.entities.Survey;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * SurveyService - class for managements list of {@link Survey}
 *
 * @author Sevler
 */
@Service
public class SurveyService {

    private HashSet<Survey> surveys = new HashSet<>();

    /**
     * Adds survey to survey list
     *
     * @param survey survey to add to the list of surveys
     */
    public void addSurvey(Survey survey) {
        surveys.add(survey);
    }

    /**
     * Remove survey for survey list
     *
     * @param survey survey to remove from survey list
     */
    public void removeSurvey(Survey survey) {
        surveys.remove(survey);
    }

    /**
     * Remove survey by id for survey list
     *
     * @param id Survey id to remove from survey list
     */
    public void removeSurvey(long id) {
        surveys.removeIf(survey -> survey.getId() == id);
    }

    /**
     * Returns the Survey by id
     *
     * @param id Survey id
     * @return survey by id
     */
    public Survey getSurvey(long id) {
        return surveys.stream()
                .filter(survey -> survey.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Returns the list of all active surveys
     *
     * @return list of all active surveys
     */
    public Set<Survey> getActiveSurveys() {
        return surveys
                .stream()
                .filter(survey -> survey.getEndTime().isAfter(LocalDateTime.now()))
                .collect(Collectors.toSet());
    }

    /**
     * Returns the Hash Set of all surveys
     *
     * @return Hash Set of all surveys
     */
    public HashSet<Survey> getAllSurveys() {
        return surveys;
    }
}
