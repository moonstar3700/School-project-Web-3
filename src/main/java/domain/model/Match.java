package domain.model;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Match {
    private int matchid;
    private LocalDate date, winnerRegistration;
    private LocalTime time;
    private String home,away, winner;
    private User creator;
    private Group group;

    public Match(LocalDate date, LocalTime time, String home, String away) {
        setDate(date);
        setTime(time);
        setHome(home);
        setAway(away);


    }


    public Match() {

    }

    public Match(String date, String time, String home, String away) {
        setDate(LocalDate.parse(date));
        setTime(LocalTime.parse(time));
        setHome(home);
        setAway(away);
    }

    public int getMatchid() {
        return matchid;
    }

    public void setMatchid(int matchid) {
        this.matchid = matchid;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        if (LocalDate.now().isAfter(date)) {
            throw new DomainException("Match must take place in the future");
        }
        this.date = date;
    }

    public LocalDate getWinnerregistration() {
        return winnerRegistration;
    }

    public void setWinnerregistration(LocalDate winnerregistration) {
        this.winnerRegistration = winnerregistration;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        if (home == null || home.isEmpty()) {
            throw new DomainException("No home club given");
        }
        this.home = home;
    }

    public String getAway() {
        return away;
    }

    public void setAway(String away) {
        if (away == null || away.isEmpty()) {
            throw new DomainException("No away club given");
        }
        this.away = away;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        if (winner == null || winner.isEmpty()) {
            throw new DomainException("No winning club given");
        }
        this.winner = winner;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        if (creator == null) {
            throw new DomainException("No creator selected");
        }
        this.creator = creator;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        if (group == null) {
            throw new IllegalArgumentException("No group given");
        }
        this.group = group;
    }
}