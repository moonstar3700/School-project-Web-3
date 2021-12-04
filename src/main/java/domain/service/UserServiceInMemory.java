package domain.service;

import domain.model.Group;
import domain.model.Role;
import domain.model.User;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceInMemory implements UserService{
    private final Map<Integer, User> users = new HashMap<Integer, User>();
    private int userid = 1;    // als je later werkt met externe databank, wordt dit userid automatisch gegenereerd

    public UserServiceInMemory() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        User administrator = new User("admin@ucll.be", "t", "Ad", "Ministrator", Group.ELITE);
        administrator.setRole(Role.ADMIN);
        add(administrator);
    }


    public User get(int userid) {
       User user = users.get(userid);
       if (user == null) {
           throw new DbException("User does not exist.");
       }
       return user;
    }

    public List<User> getAll() {
        return new ArrayList<User>(users.values());
    }

    @Override
    public List<User> getAllGroup(User userloged) {
        return null;
    }

    @Override
    public List<User> getAllGroupWithTraining(User userloged) {
        return null;
    }

    @Override
    public List<User> getAllWithTraining() {
        return null;
    }

    @Override
    public List<User> getAllGroupWithTrainingOnDate(LocalDate date, User userloged) {
        return null;
    }

    @Override
    public List<User> getAllWithTrainingOnDate(LocalDate date) {
        return null;
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

    @Override
    public User checkEmail(String email) {
        return null;
    }

    public int getNumberOfUsers() {
        return users.size();
    }
}
