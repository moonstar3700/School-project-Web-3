package domain.service;

import domain.model.Match;
import util.DBConnectionService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MatchServiceDB implements MatchService {
    private Connection connection;
    private String schema;

    public MatchServiceDB(){
        this.connection = DBConnectionService.getDBConnection();
        this.schema = DBConnectionService.getSchema();
    }

    @Override
    public void add(Match match) {

    }

    @Override
    public void get(int matchid) {

    }

    @Override
    public List<Match> getAll() {
        return null;
    }

    @Override
    public void update(Match match) {

    }

    @Override
    public void delete(Match match) {

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
