package domain.service;

import domain.model.Match;
import domain.model.User;
import util.DBConnectionService;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchServiceDB implements MatchService {
    private final Map<Integer, Match> matches = new HashMap<Integer, Match>();
    private Connection connection;
    private String schema;

    public MatchServiceDB(){
        this.connection = DBConnectionService.getDBConnection();
        this.schema = DBConnectionService.getSchema();
    }

    @Override
    public void add(Match match) {
        String query = String.format("insert into %s.match (matchdate,matchtime,home,away,user_id) values (?,?,?,?,?)", schema);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, Date.valueOf(match.getDate()));
            preparedStatement.setTime(2, Time.valueOf(match.getTime()));
            preparedStatement.setString(3, match.getHome());
            preparedStatement.setString(4, match.getAway());
            preparedStatement.setInt(5, match.getCreator().getUserid());
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
        return null;
    }

    @Override
    public void update(Match match) {

    }

    @Override
    public void delete(int matchid) {

    }

    /**
     * Check the connection and reconnect when necessary
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
