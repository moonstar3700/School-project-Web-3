package domain.service;

import domain.model.DomainException;
import domain.model.Group;
import domain.model.Match;
import domain.model.User;
import util.DBConnectionService;

import javax.validation.constraints.Null;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchServiceDB implements MatchService {
    private final Map<Integer, Match> matches = new HashMap<Integer, Match>();
    private Connection connection;
    private String schema;
    private UserServiceDB users;

    public MatchServiceDB() {
        this.connection = DBConnectionService.getDBConnection();
        this.schema = DBConnectionService.getSchema();
    }

    @Override
    public void add(Match match) {
        String query = String.format("insert into %s.match (matchdate,matchtime,home,away,user_id,match_group) values (?,?,?,?,?,?)", schema);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, Date.valueOf(match.getDate()));
            preparedStatement.setTime(2, Time.valueOf(match.getTime()));
            preparedStatement.setString(3, match.getHome());
            preparedStatement.setString(4, match.getAway());
            preparedStatement.setInt(5, match.getCreator().getUserid());
            preparedStatement.setString(6, match.getCreator().getGroup().getStringValue());
            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Match get(int matchid) {
        Match match = matches.get(matchid);
        if (match == null) {
            throw new DbException("Match does not exist.");
        }
        return match;
    }

    @Override
    public List<Match> getAll() {
        String query = String.format("SELECT * from %s.match order by matchdate, matchtime;", schema);

        PreparedStatement statementInsert = null;
        try {
            statementInsert = connection.prepareStatement(query);
            ResultSet resultSet = statementInsert.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("match_id");
                LocalDate matchDate = resultSet.getDate("matchdate").toLocalDate();
                LocalTime matchTime = resultSet.getTime("matchtime").toLocalTime();
                String home = resultSet.getString("home");
                String away = resultSet.getString("away");
                int userid = resultSet.getInt("user_id");
                User user;
                try {
                    user = users.get(userid); //dit is het niet maar ik weet effe niet wat het wel moet zijn

                } catch (NullPointerException exc) {
                    user = new User("deleted@user.com", "deleted", "Deleted", "User", Group.ELITE); //group moet eigenlijk in match table
                }
                String groupstring = resultSet.getString("match_group");
                user.setGroup(groupstring);
                Group group = user.getGroup();
                Match match = new Match(id, matchDate, matchTime, home, away, user, group);
                matches.put(id, match);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return new ArrayList<Match>(matches.values());
    }

    @Override
    public void update(Match match) {
        if (match == null) {
            throw new DbException("No user given");
        }
        int matchid = match.getMatchid();
        if (match.getWinner() == null) {
            String query = String.format("update %s.match set home = ?, away = ?, matchdate = ?, matchtime = ?,  where match_id = ?", schema);
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, match.getHome());
                preparedStatement.setString(2, match.getAway());
                preparedStatement.setDate(3, Date.valueOf(match.getDate()));
                preparedStatement.setTime(4, Time.valueOf(match.getTime()));
                preparedStatement.setInt(5, matchid);
                preparedStatement.executeUpdate();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {

            String query = String.format("update %s.match set home = ?, away = ?, matchdate = ?, matchtime = ?, winner = ?, winnerregistration = ? where match_id = ?", schema);
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, match.getHome());
                preparedStatement.setString(2, match.getAway());
                preparedStatement.setDate(3, Date.valueOf(match.getDate()));
                preparedStatement.setTime(4, Time.valueOf(match.getTime()));
                preparedStatement.setString(5, match.getWinner());
                preparedStatement.setDate(6, Date.valueOf(match.getWinnerregistration()));
                preparedStatement.setInt(7, matchid);
                preparedStatement.executeUpdate();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    /**
     * Check the connection and reconnect when necessary
     *
     * @return the connection with the db, if there is one
     */
    private Connection getConnection() {
        checkConnection();
        return this.connection;
    }

    /**
     * Check if the connection is still open
     * When connection has been closed: reconnect
     */
    private void checkConnection() {
        try {
            if (this.connection == null || this.connection.isClosed()) {
                System.out.println("Connection has been closed");
                this.reConnect();
            }
        } catch (SQLException throwables) {
            throw new DbException(throwables.getMessage());
        }
    }

    /**
     * Reconnects application to db
     */
    private void reConnect() {
        DBConnectionService.disconnect();   // close connection with db properly
        DBConnectionService.reconnect();      // reconnect application to db server
        this.connection = DBConnectionService.getDBConnection();    // assign connection to DBSQL
    }
}
