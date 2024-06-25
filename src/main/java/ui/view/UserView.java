package ui.view;

import domain.model.Group;
import domain.model.Role;
import domain.model.User;
import domain.service.AppService;
import util.Secret;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.sql.*;

public class UserView {

    private static String sha512(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest crypt = MessageDigest.getInstance("SHA-512");
        crypt.reset();

        // encrypts
        crypt.update(password.getBytes("UTF-8"));

        //16 hexadecimal system the sixteen digits are "0–9" followed by "A–F".
        String hashedPassword = new BigInteger(1, crypt.digest()).toString(16);
        System.out.println(hashedPassword.length());
        return hashedPassword;
    }

    public static void main(String[] args){
        // constants for your project
        // replace "probeer" by your own database, e.g. '2TX34'
        String url = "jdbc:postgresql://localhost:5432/web3";
        // replace 'web3' by your own schema name, e.g. groep102
        String schema = "groep1_17";




        /*try {
            AppService service = new AppService();
            User userInsert = new User();
            userInsert.setEmail("admin@admin.be");
            userInsert.setHashedPassword("t");
            userInsert.setFirstName("ad");
            userInsert.setLastName("min");
            userInsert.setGroup(Group.ELITE);
            userInsert.setRole(Role.ADMIN);
            service.add(userInsert);
        }
        catch (IllegalArgumentException | UnsupportedEncodingException | NoSuchAlgorithmException exc) {

        }*/



        User userInsert = new User();

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
            /*preparedStatement.setString(1, "Patryk@ucll.be");
            preparedStatement.setString(2, "p");
            preparedStatement.setString(3, "Patryk");
            preparedStatement.setString(4, "Piekarz");
            preparedStatement.setString(5, "ELITE");
            preparedStatement.setString(6, "TRAINER");
            preparedStatement.execute();*/

            preparedStatement.setString(1, "admin@admin.be");
            preparedStatement.setString(2, sha512("t"));
            preparedStatement.setString(3, "Ad");
            preparedStatement.setString(4, "Min");
            preparedStatement.setString(5, "ELITE");
            preparedStatement.setString(6, "ADMIN");
            preparedStatement.execute();

            //get all
            query = String.format("SELECT * from %s.user order by user_id;", schema);
            PreparedStatement statementInsert = connection.prepareStatement(query);
            ResultSet resultSet = statementInsert.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("user_id");
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
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
