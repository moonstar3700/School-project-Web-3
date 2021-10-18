package domain.service;

import domain.model.Match;
import domain.model.User;

import java.util.List;

public class AppService {
    private UserService users = new UserServiceInMemory();
    private MatchService matches = new MatchServiceInMemory();

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

    public void addMatch(Match match) {
        matches.add(match);
    }

    public void getMatch(int matchid) {
        matches.get(matchid);
    }
    public List<Match> getAllMatches() {
        return matches.getAll();
    }
    public void updateMatch(Match match) {
        matches.update(match);
    }
    public void deleteMatch(int matchid) {
        matches.delete(matchid);
    }
}
