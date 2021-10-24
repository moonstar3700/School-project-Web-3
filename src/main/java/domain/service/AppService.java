package domain.service;

import domain.model.Match;
import domain.model.Training;
import domain.model.User;

import java.util.ArrayList;
import java.util.List;

public class AppService {
    private UserService users = new UserServiceDB();
    //private MatchService matches = new MatchServiceInMemory();
    private MatchService matches = new MatchServiceDB();
    private TrainingService trainings = new TrainingServiceDB();

    public void add(User user) {
        users.add(user);
    }

    public User get(int userid) {
        return users.get(userid);
    }

    ;

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

    public Match getMatch(int matchid) {
        return matches.get(matchid);
    }


    public List<Match> getAllMatches() {
        return matches.getAll();
    }

    public void updateMatch(Match match) {
        matches.update(match);
    }

    public void addTraining(Training training) {
        trainings.add(training);
    }

    public void getTraining(int trainingid) {
        trainings.get(trainingid);
    }

    public List<Training> getAllTrainings() {
        return trainings.getAll();
    }

    public void updateTraining(Training training) {
        trainings.update(training);
    }

    public void deleteTraining(int trainingid) {
        trainings.delete(trainingid);
    }

    public ArrayList<Integer> getDurations() {
        return trainings.getDurations();
    }

    public Match searchMatches(String home, String away, String group) {
        return matches.search(home, away, group);
    }
}
