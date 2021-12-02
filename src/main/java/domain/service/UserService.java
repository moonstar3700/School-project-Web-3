package domain.service;

import domain.model.User;

import java.util.List;

public interface UserService {

    void add(User user);
    User get(int userid);
    List<User> getAll();
    List<User> getAllGroup(User userloged);
    List<User> getAllGroupWithTraining(User userloged);
    void update(User user);
    void delete(int userid);
    User checkEmail(String email);
    int getNumberOfUsers();
}
