package ru.furestry.fabriquetestapp.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.furestry.fabriquetestapp.entities.Answer;
import ru.furestry.fabriquetestapp.entities.Question;
import ru.furestry.fabriquetestapp.entities.Survey;
import ru.furestry.fabriquetestapp.entities.User;
import ru.furestry.fabriquetestapp.services.SurveyService;
import ru.furestry.fabriquetestapp.services.UserService;

import java.time.LocalDateTime;
import java.util.*;

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
    public Survey getSurvey(@RequestParam long id) {
        return surveyService.getSurvey(id);
    }

    /**
     * Send user answers for survey
     *
     * @param body Request body contains User id, Survey id and answers(Question id and answer)
     * @return HttpStatus may be Not Found(404), Too Early(425) or Locked(423)
     */
    @PostMapping("/survey")
    public HttpStatus sendSurvey(@RequestBody JsonNode body) {
        JsonMapper mapper = new JsonMapper();
        long userId = body.get("userId").asLong();
        long surveyId = body.get("surveyId").asLong();

        List<JsonNode> answers = mapper.convertValue(body.get("answers"), List.class);

        User user = new User(userId);
        Survey survey = surveyService.getSurvey(surveyId);
        Map<Survey, List<Answer>> answerMap = new HashMap<>();
        List<Answer> answerList = new ArrayList<>();

        if (survey == null) {
            return HttpStatus.NOT_FOUND;
        }

        if (LocalDateTime.now().isBefore(survey.getStartTime())) {
            return HttpStatus.TOO_EARLY;
        }

        if (LocalDateTime.now().isAfter(survey.getEndTime())) {
            return HttpStatus.LOCKED;
        }

        answers.forEach(answer -> {
            long questionId = answer.get("questionId").asLong();
            String answerText = answer.get("answer").asText();

            Question question = survey.getQuestions().stream()
                    .filter(q -> q.getId() == questionId)
                    .findFirst()
                    .orElse(null);

            if (question != null) {
                answerList.add(new Answer(question, answerText));
            }
        });

        if (answerList.size() == 0) {
            return HttpStatus.BAD_REQUEST;
        }

        answerMap.put(survey, answerList);
        user.setAnswers(answerMap);
        userService.addUser(user);

        return HttpStatus.OK;

    }
}
