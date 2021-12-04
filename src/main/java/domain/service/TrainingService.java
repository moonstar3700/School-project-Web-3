package domain.service;

import domain.model.Match;
import domain.model.Training;
import domain.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface TrainingService {
    void add(Training training, User user);
    Training get(int training);
    List<Training> getAll(User user);
    List<Training> getAllZonderUser();
    List<Training> getAllZonderUserOnDate(LocalDate date);
    void update(Training training, User user);
    void delete(int trainingid);
    List<Training> getAllFiltered(String filter, User user);
    List<Training> getAllZonderUserFiltered(String filter);
    ArrayList<Training> searchByDate(LocalDate date, User user);
}
