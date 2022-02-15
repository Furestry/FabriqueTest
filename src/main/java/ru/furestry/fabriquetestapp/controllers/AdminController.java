package ru.furestry.fabriquetestapp.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.furestry.fabriquetestapp.services.SurveyService;
import ru.furestry.fabriquetestapp.services.UserService;

/**
 * Controller for admin pages
 *
 * @author Sevler
 */
@RestController
public class AdminController {

    private UserService userService;
    private SurveyService surveyService;

    /**
     * Class constructor
     *
     * @param userService user service
     * @param surveyService survey service
     */
    public AdminController(UserService userService, SurveyService surveyService) {
        this.userService = userService;
        this.surveyService = surveyService;
    }

    /**
     *
     *
     */
    @PostMapping("/admin")
    public void adminPageEdit() {

    }
}
