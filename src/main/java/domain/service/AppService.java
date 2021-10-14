package domain.service;

import domain.model.User;

import java.util.List;

public class AppService {
    private UserService users = new UserServiceDB();

    public void add(User user) {
        users.add(user);
    }

    public User get(int userid) {
        return users.get(userid);
    };
    public List<User> getAll() {
        return users.getAll();
    }
    public void update(User user) {
        users.update(user);
    }
    public void delete(int userid) {
        users.delete(userid);
    }
    int getNumberOfUsers() {
        return users.getNumberOfUsers();
    }
}
