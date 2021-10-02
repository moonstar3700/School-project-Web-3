package domain.service;

import domain.model.Group;
import domain.model.Role;
import domain.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {
    private final Map<Integer, User> users = new HashMap<Integer, User>();
    private int userid = 1;    // als je later werkt met externe databank, wordt dit userid automatisch gegenereerd

    public UserService() {
        User administrator = new User("admin@ucll.be", "t", "Ad", "Ministrator", Group.ELITE);
        administrator.setRole(Role.ADMIN);
        add(administrator);
    }

    public User get(int userid) {
        return users.get(userid);
    }

    public List<User> getAll() {
        return new ArrayList<User>(users.values());
    }

    public void add(User user) {
        if (user == null) {
            throw new DbException("No user given");
        }
        if (users.containsKey(user.getUserid())) {
            throw new DbException("User already exists");
        }
        for (User u : users.values()) {
            if (user.getEmail().equals(u.getEmail())) {
                throw new DbException("Email already in use");
            }
        }
        user.setUserid(userid);   // user toevoegen geeft altijd nieuw userid
        users.put(user.getUserid(), user);
        userid++;
    }

    public void update(User user) {
        if (user == null) {
            throw new DbException("No user given");
        }
        if (!users.containsKey(user.getUserid())) {
            throw new DbException("No user found");
        }
        users.put(user.getUserid(), user); // user updaten: userid blijft behouden
    }

    public void delete(int userid) {
        users.remove(userid);   // userid gaat verloren, maar wordt niet ingenomen door eventuele nieuwe user
    }

    public int getNumberOfUsers() {
        return users.size();
    }
}
