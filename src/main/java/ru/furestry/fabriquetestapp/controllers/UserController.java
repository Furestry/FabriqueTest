package ru.furestry.fabriquetestapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.furestry.fabriquetestapp.entities.Survey;
import ru.furestry.fabriquetestapp.entities.User;
import ru.furestry.fabriquetestapp.services.SurveyService;
import ru.furestry.fabriquetestapp.services.UserService;

import java.util.List;

/**
 * Controller for user pages
 *
 * @author Sevler
 */
@RestController
public class UserController {

    private UserService userService;
    private SurveyService surveyService;

    /**
     * Class constructor
     *
     * @param userService user service
     * @param surveyService survey service
     */
    public UserController(UserService userService, SurveyService surveyService) {
        this.userService = userService;
        this.surveyService = surveyService;
    }

    /**
     * Returns REST user
     *
     * @param id user id
     * @return REST user by id if exists or user with id = 1
     */
    @GetMapping("/user")
    public User getUserInfo(@RequestParam(defaultValue = "1") long id) {
        return userService.getUser(id);
    }

    /**
     * Returns REST list of current active surveys
     *
     * @return REST list of current active surveys
     */
    @GetMapping("/surveys")
    public List<Survey> getSurveys() {
        return surveyService.getActiveSurveys();
    }

    /**
     * Returns REST survey by id
     *
     * @param id survey id
     * @return REST survey by id
     */
    @GetMapping("/survey")
    public Survey getSurvey(@RequestParam(defaultValue = "1") long id) {
        return surveyService.getSurvey(id);
    }

    /**
     *
     * @return string
     */
    @PostMapping("/survey")
    public String sendSurvey() {
        return "";
    }
}
