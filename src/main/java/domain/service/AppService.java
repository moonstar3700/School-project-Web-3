package domain.service;

import domain.model.User;

public class AppService {
    private UserService users = new UserServiceDB();

    public void add(User user) {
        users.add(user);
    }


}
