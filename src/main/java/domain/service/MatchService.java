package domain.service;

import domain.model.Match;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface MatchService {

    void add(Match match);
    Match get(int matchid);
    List<Match> getAll();
    void update(Match match);

    Match search(String home, String away, String group);

    ArrayList<Match> searchByDate(LocalDate date);

    void delete(int matchid);
}
