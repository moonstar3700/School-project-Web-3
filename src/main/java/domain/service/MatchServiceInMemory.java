package domain.service;

import domain.model.Group;
import domain.model.Match;
import domain.model.Role;
import domain.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchServiceInMemory implements MatchService{
    private final Map<Integer, Match> matches = new HashMap<Integer, Match>();
    private int matchid = 1;    // als je later werkt met externe databank, wordt dit userid automatisch gegenereerd

    public MatchServiceInMemory() {

    }


    public Match get(int matchid) {
       Match match = matches.get(matchid);
       if (match == null) {
           throw new DbException("Match does not exist.");
       }
       return match;
    }

    public List<Match> getAll() {
        return new ArrayList<Match>(matches.values());
    }

    public void add(Match match) {
        if (match == null) {
            throw new DbException("No match given");
        }
        if (matches.containsKey(match.getMatchid())) {
            throw new DbException("Match already exists");
        }
        match.setMatchid(matchid);   // user toevoegen geeft altijd nieuw userid
        matches.put(match.getMatchid(), match);
        matchid++;
    }

    public void update(Match match) {
        if (match == null) {
            throw new DbException("No match given");
        }
        if (!matches.containsKey(match.getMatchid())) {
            throw new DbException("No match found");
        }
        matches.put(match.getMatchid(), match); // user updaten: userid blijft behouden
    }

    @Override
    public Match search(String home, String away, String group) {
        return null;
    }

    @Override
    public ArrayList<Match> searchByDate(LocalDate date) {
        return null;
    }

    public void delete(int matchid) {
        matches.remove(matchid);   // userid gaat verloren, maar wordt niet ingenomen door eventuele nieuwe user
    }

    public int getNumberOfMatches() {
        return matches.size();
    }
}
