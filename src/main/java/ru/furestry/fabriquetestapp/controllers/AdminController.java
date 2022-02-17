package ru.furestry.fabriquetestapp.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.furestry.fabriquetestapp.entities.Question;
import ru.furestry.fabriquetestapp.entities.Survey;
import ru.furestry.fabriquetestapp.services.SurveyService;

import java.time.LocalDateTime;
import java.util.HashSet;

/**
 * Controller for admin pages
 *
 * @author Sevler
 */
@RestController
public class AdminController {

    private SurveyService surveyService;

    /**
     * Class constructor
     *
     * @param surveyService survey service
     */
    public AdminController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    /**
     * Edit survey param by id
     *
     * @param body Request body contains Survey id, editing parameter name and new parameter value
     */
    @PostMapping("/admin")
    public void editSurvey(@RequestBody JsonNode body) {
        JsonMapper mapper = new JsonMapper();

        long id = body.get("id").asLong();
        JsonNode value = body.get("value");
        Survey survey = surveyService.getSurvey(id);

        switch (body.get("param").asText()) {
            case "questions" -> {
                surveyService.removeSurvey(survey);
                survey.setQuestions(mapper.convertValue(value, HashSet.class));
                surveyService.addSurvey(survey);
            }

            case "description" -> {
                surveyService.removeSurvey(survey);
                survey.setDescription(value.asText());
                surveyService.addSurvey(survey);
            }

            case "endTime" -> {
                surveyService.removeSurvey(survey);
                survey.setEndTime(mapper.convertValue(value, LocalDateTime.class));
                surveyService.addSurvey(survey);
            }
        }
    }

    /**
     * Create survey
     *
     * @param id Request param - new Survey id
     * @param description Request param - Survey description
     * @param questions Request body - questions contains id, text and type
     * @param startTime Request param - start time in ISO format
     * @param endTime Request param - end time in ISO format
     */
    @PutMapping("/admin")
    public void createSurvey(
            @RequestParam long id,
            @RequestParam String description,
            @RequestBody HashSet<Question> questions,
            @RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime
    ) {
        surveyService.addSurvey(
                new Survey(id, startTime, endTime, description, questions)
        );
    }

    /**
     * Delete survey for survey list
     *
     * @param id Request param - Survey id
     */
    @DeleteMapping
    public void deleteSurvey(@RequestParam long id) {
        surveyService.removeSurvey(id);
    }
}
