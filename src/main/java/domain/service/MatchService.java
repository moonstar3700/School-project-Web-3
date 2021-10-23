package domain.service;

import domain.model.Match;

import java.util.List;

public interface MatchService {

    void add(Match match);
    Match get(int matchid);
    List<Match> getAll();
    void update(Match match);
}
