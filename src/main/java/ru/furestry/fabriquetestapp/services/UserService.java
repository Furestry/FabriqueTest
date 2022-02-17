package ru.furestry.fabriquetestapp.services;

import org.springframework.stereotype.Service;
import ru.furestry.fabriquetestapp.entities.Answer;
import ru.furestry.fabriquetestapp.entities.Survey;
import ru.furestry.fabriquetestapp.entities.User;

import java.util.HashSet;
import java.util.Map;

/**
 * UserService - class for managements list of {@link User}
 *
 * @author Sevler
 */
@Service
public class UserService {

    private HashSet<User> users = new HashSet<>();

    /**
     * Adds user to user list
     *
     * @param user user to add to the list of users
     */
    public void addUser(User user) {
        User containedUser = getUser(user.getId());

        if (users.contains(user)) {
            Map<Survey, HashSet<Answer>> surveys = containedUser.getAnswers();

            surveys.putAll(user.getAnswers());

            containedUser.setAnswers(surveys);

            users.add(containedUser);
        } else {
            users.add(user);
        }
    }

    /**
     * Returns the User by id
     *
     * @param id User id
     * @return user by id
     */
    public User getUser(long id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(new User(id));
    }
}
