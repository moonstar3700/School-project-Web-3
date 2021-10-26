package domain.service;

import domain.model.Match;
import domain.model.Training;

import java.util.ArrayList;
import java.util.List;

public interface TrainingService {
    void add(Training training);
    Training get(int training);
    List<Training> getAll();
    void update(Training training);
    void delete(int trainingid);
    List<Training> getAllFiltered(String filter);

}
