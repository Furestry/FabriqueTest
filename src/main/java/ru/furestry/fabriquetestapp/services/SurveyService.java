package ru.furestry.fabriquetestapp.services;

import org.springframework.stereotype.Service;
import ru.furestry.fabriquetestapp.entities.Survey;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SurveyService - class for managements list of {@link Survey}
 *
 * @author Sevler
 */
@Service
public class SurveyService {

    private List<Survey> surveys = new ArrayList<>();

    /**
     * Adds survey to survey list
     *
     * @param survey survey to add to the list of surveys
     */
    public void addSurvey(Survey survey) {
        surveys.add(survey);
    }

    /**
     * Change end time of survey
     *
     * @param survey survey to change time
     * @param time time to change
     * @return survey with changed time
     */
    public Survey changeEndTime(Survey survey, LocalDateTime time) {
        boolean contains = surveys.contains(survey);
        survey.setEndTime(time);

        if (contains) {
            surveys.set(surveys.indexOf(survey), survey);
        }

        return survey;
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
                .orElse(new Survey());
    }

    /**
     * Returns the list of all active surveys
     *
     * @return list of all active surveys
     */
    public List<Survey> getActiveSurveys() {
        return surveys
                .stream()
                .filter(survey -> survey.getEndTime().isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());
    }

    /**
     * Returns the list of all surveys
     *
     * @return list of all surveys
     */
    public List<Survey> getAllSurveys() {
        return surveys;
    }
}
