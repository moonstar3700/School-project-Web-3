package util;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Service class
 * Gives information about connection to DBSQL
 * Hides underlying details concerning database
 */
public class DBConnectionService {
    private static Connection dbConnection;
    private static String schema, path;

    public static String getSchema() {
        return schema;
    }

    public static Connection getDBConnection() {
        return dbConnection;
    }

    public static void connect(String dbURL, String searchPath) {
        schema = searchPath;
        path = dbURL;
        DBConnectionManager connectionManager = DBConnectionManager.getInstance(dbURL);
        dbConnection = connectionManager.getConnection();
    }

    public static void reconnect() {
        connect(schema, path);
    }

    public static void disconnect() {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            throw new ConnectionException(e);
        }
    }
}
