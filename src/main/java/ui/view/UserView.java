package ui.view;

import domain.model.Group;
import domain.model.User;
import util.Secret;

import java.util.Properties;
import java.sql.*;

public class UserView {
    public static void main(String[] args){
        // constants for your project
        // replace "probeer" by your own database, e.g. '2TX34'
        String url = "jdbc:postgresql://databanken.ucll.be:62122/2TX32";
        // replace 'web3' by your own schema name, e.g. groep102
        String schema = "groep1_17";

        // set properties for db connection
        Properties properties = new Properties();

        try {
            Class.forName("util.Secret");
            Secret.setPass(properties);
        } catch (ClassNotFoundException e) {
            System.out.println("Class ui.view.Secret with credentials not found");
        }

        properties.setProperty("ssl", "true");
        properties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
        properties.setProperty("sslmode", "prefer");

        //open the db connection
        try (Connection connection = DriverManager.getConnection(url, properties)) {

            // add
            String query = String.format("insert into %s.user (email,password,firstname,lastname,\"group\",role) values (?,?,?,?,?,?)", schema);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "Patryk@ucll.be");
            preparedStatement.setString(2, "p");
            preparedStatement.setString(3, "Patryk");
            preparedStatement.setString(4, "Piekarz");
            preparedStatement.setString(5, "ELITE");
            preparedStatement.setString(6, "TRAINER");
            preparedStatement.execute();

            //get all
            query = String.format("SELECT * from %s.user order by id;", schema);
            PreparedStatement statementInsert = connection.prepareStatement(query);
            ResultSet resultSet = statementInsert.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                String group = resultSet.getString("group");
                String role = resultSet.getString("role");
                User user = new User (email, password, firstname, lastname, group, role);
                System.out.println(user.toString());
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Connection no succes");
        }
    }
}
