package domain.service;

import domain.model.User;

import java.util.List;

public interface UserService {

    void add(User user);
    User get(int userid);
    List<User> getAll();
    void update(User user);
    void delete(int userid);
    int getNumberOfUsers();
}
