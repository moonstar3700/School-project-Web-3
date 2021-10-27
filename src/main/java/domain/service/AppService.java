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

    public User checkUserMail(String mail) {
        return users.checkEmail(mail);
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

    public void addTraining(Training training, User user) {
        trainings.add(training, user);
    }

    public Training getTraining(int trainingid) {
        return trainings.get(trainingid);
    }

    public List<Training> getAllTrainings(User user) {
        return trainings.getAll(user);
    }

    public void updateTraining(Training training) {
        trainings.update(training);
    }

    public void deleteTraining(int trainingid) {
        trainings.delete(trainingid);
    }


    public Match searchMatches(String home, String away, String group) {
        return matches.search(home, away, group);
    }

    public List<Training> getAllTrainingsFilter(String filter, User user){return trainings.getAllFiltered(filter, user);}

    public void deleteMatch(int matchid) {
        matches.delete(matchid);
    }
}
