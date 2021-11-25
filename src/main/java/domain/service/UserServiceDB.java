package domain.service;

import domain.model.User;
import util.DBConnectionService;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceDB implements UserService {
    private final Map<Integer, User> users = new HashMap<Integer, User>();
    private Connection connection;
    private String schema;

    public UserServiceDB() {
        this.connection = DBConnectionService.getDBConnection();
        this.schema = DBConnectionService.getSchema();
    }

    @Override
    public void add(User user) {
        String query = String.format("insert into %s.user (email,password,firstname,lastname,\"group\",role) values (?,?,?,?,?,?)", schema);
        List<User> userList = getAll();    //new ArrayList<User>(users.values()); // getAll() ?
        for (User u : userList) {
            if (u.getEmail().equals(user.getEmail())) {
                throw new DbException("Email already in use");
            }
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getGroup().getStringValue());
            preparedStatement.setString(6, user.getRole().getStringValue());
            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public User get(int userid) {
        User user = users.get(userid);
        if (user == null) {
            throw new DbException("User does not exist.");
        }
        return user;

        //moet dit niet met een query?

        /* String query = String.format("SELECT * from %s.user where user_id = ?;", schema);
        User user = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userid);
            ResultSet resultSet = preparedStatement.executeQuery();
            int id = resultSet.getInt("user_id");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            String firstname = resultSet.getString("firstname");
            String lastname = resultSet.getString("lastname");
            String group = resultSet.getString("group");
            String role = resultSet.getString("role");
            user = new User (id, email, password, firstname, lastname, group, role);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return user; */
    }

    @Override
    public List<User> getAll() {
        String query = String.format("SELECT * from %s.user order by user_id;", schema);

        PreparedStatement statementInsert = null;
        try {
            statementInsert = connection.prepareStatement(query);
            ResultSet resultSet = statementInsert.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("user_id");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                String group = resultSet.getString("group");
                String role = resultSet.getString("role");
                User user = new User(id, email, password, firstname, lastname, group, role);
                users.put(id, user);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return new ArrayList<User>(users.values());
    }

    /*@Override
    public List<User> getAllGroup() {
        Map<Integer, User> usersGroup = new HashMap<Integer, User>();

    }*/

    @Override
    public void update(User user) {
        if (user == null) {
            throw new DbException("No user given");
        }
        List<User> userList = new ArrayList<User>(users.values());
        for (User u : userList) {
            if (u.getEmail().equals(user.getEmail()) && u.getUserid() != user.getUserid()) {
                throw new DbException("Email already in use");
            }
        }
        int userid = user.getUserid();
        String query = String.format("update %s.user set email = ?, password = ?, firstname = ?, " +
                "lastname = ?, \"group\" = ?, \"role\" = ? where user_id = ?", schema);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, String.valueOf(user.getGroup()));
            preparedStatement.setString(6, String.valueOf(user.getRole()));
            preparedStatement.setInt(7, userid);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void delete(int userid) {
        String query = String.format("delete from %s.user where user_id = ?", schema);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userid);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public User checkEmail(String email) {
        List<User> userList = getAll();
        User user = null;
        for (User u : userList) {
            if (email.equals(u.getEmail())) {
                user = u;
            }
        }
        if (user == null) {
            throw new DbException("No user with this email");
        }
        return user;
    }

    @Override
    public int getNumberOfUsers() {
        return users.size();
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
