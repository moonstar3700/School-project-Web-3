package domain.service;

import domain.model.DomainException;
import domain.model.Match;
import domain.model.Training;
import domain.model.User;
import util.DBConnectionService;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.time.temporal.ChronoUnit.MINUTES;

public class TrainingServiceDB implements TrainingService {
    private final Map<Integer, Training> trainings = new TreeMap<Integer, Training>();
    private final Map<Integer, Training> trainingsfiltered = new TreeMap<Integer, Training>();
    private Connection connection;
    private String schema;
    private UserServiceDB users;
    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public TrainingServiceDB() {

        this.connection = DBConnectionService.getDBConnection();
        this.schema = DBConnectionService.getSchema();
    }

    @Override
    public void add(Training training, User user) {
        String query = String.format("insert into %s.training (training_date, training_start, training_end, user_id) values (?,?,?,?)", schema);
        List<Training> list = getAll(user);
        for (Training t : list) {
            if (training.getDate().equals(t.getDate()) && training.getStart().equals(t.getStart()) && training.getEnd().equals(t.getEnd())) {
                throw new DomainException("Training already exists");
            }
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, Date.valueOf(training.getDate()));
            preparedStatement.setTime(2, Time.valueOf(training.getStart()));
            preparedStatement.setTime(3, Time.valueOf(training.getEnd()));
            //preparedStatement.setInt(4, training.getDuration());
            preparedStatement.setInt(4, training.getCreator().getUserid());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Training get(int trainingid) {
        Training training = trainings.get(trainingid);
        if (training == null) {
            throw new DomainException("Training does not exist.");
        }
        return training;
    }

    @Override
    public List<Training> getAll(User user) {
        trainings.clear();
        String query = String.format("SELECT * from %s.training where user_id = ? order by training_id asc;", schema);
        PreparedStatement statementInsert = null;
        try {
            statementInsert = connection.prepareStatement(query);
            statementInsert.setInt(1, user.getUserid());
            ResultSet resultSet = statementInsert.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("training_id");
                LocalDate date = resultSet.getDate("training_date").toLocalDate();
                LocalTime start = resultSet.getTime("training_start").toLocalTime();
                LocalTime end = resultSet.getTime("training_end").toLocalTime();

                int user_id = resultSet.getInt("user_id");
                Training training = new Training(id, date, start, end, user_id);
                trainings.put(id, training);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new ArrayList<Training>(trainings.values());
    }

    @Override
    public List<Training> getAllZonderUser(){
        trainings.clear();
        String query = String.format("select user_id, training_id, training_date, training_start, training_end from %s.training \n" +
                "left outer join %s.user using(user_id)\n" +
                "where email is null", schema, schema);
        PreparedStatement statementInsert = null;
        try {
            statementInsert = connection.prepareStatement(query);;
            ResultSet resultSet = statementInsert.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("training_id");
                LocalDate date = resultSet.getDate("training_date").toLocalDate();
                LocalTime start = resultSet.getTime("training_start").toLocalTime();
                LocalTime end = resultSet.getTime("training_end").toLocalTime();

                int user_id = resultSet.getInt("user_id");
                Training training = new Training(id, date, start, end, user_id);
                trainings.put(id, training);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new ArrayList<Training>(trainings.values());
    }
    @Override
    public List<Training> getAllZonderUserOnDate(LocalDate date){
        trainings.clear();
        String query = String.format("select user_id, training_id, training_date, training_start, training_end from %s.training \n" +
                "left outer join %s.user using(user_id)\n" +
                "where email is null and training_date = ? ", schema, schema);
        PreparedStatement statementInsert = null;
        try {
            statementInsert = connection.prepareStatement(query);
            statementInsert.setDate(1, Date.valueOf(date));
            ResultSet resultSet = statementInsert.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("training_id");
                LocalDate date1 = resultSet.getDate("training_date").toLocalDate();
                LocalTime start = resultSet.getTime("training_start").toLocalTime();
                LocalTime end = resultSet.getTime("training_end").toLocalTime();

                int user_id = resultSet.getInt("user_id");
                Training training = new Training(id, date1, start, end, user_id);
                trainings.put(id, training);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new ArrayList<Training>(trainings.values());
    }

    @Override
    public List<Training> getAllFiltered(String filter, User user) {
        //String query = String.format("SELECT * from %s.training order by (?) asc;", schema);
        String query;
        PreparedStatement statementInsert = null;
        List<Training> lijst = new ArrayList<>();
        try {
            switch (filter) {
                case "training_id":
                    query = String.format("SELECT * from %s.training where user_id = ? order by training_id asc;", schema);
                    break;
                case "training_date":
                    query = String.format("SELECT * from %s.training where user_id = ? order by training_date, training_start asc;", schema);
                    break;
                case "training_start":
                    query = String.format("SELECT * from %s.training where user_id = ? order by training_start;", schema);
                    break;
                case "training_end":
                    query = String.format("SELECT * from %s.training where user_id = ? order by training_end, training_start asc;", schema);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + filter);
            }
            statementInsert = connection.prepareStatement(query);
            statementInsert.setInt(1, user.getUserid());
            ResultSet resultSet = statementInsert.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("training_id");
                LocalDate date = resultSet.getDate("training_date").toLocalDate();
                LocalTime start = resultSet.getTime("training_start").toLocalTime();
                LocalTime end = resultSet.getTime("training_end").toLocalTime();

                int user_id = resultSet.getInt("user_id");
                Training training = new Training(id, date, start, end, user_id);
                lijst.add(training);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lijst;
    }

    @Override
    public List<Training> getAllZonderUserFiltered(String filter) {
        String query = "";
        PreparedStatement statementInsert = null;
        List<Training> lijst = new ArrayList<>();
        try{


            switch (filter) {
                case "training_id":
                    query = String.format("select user_id, training_id, training_date, training_start, training_end from %s.training \n" +
                            "left outer join %s.user using(user_id)\n" +
                            "where email is null order by training_id asc ;", schema, schema);
                    break;
                case "training_date":
                    query = String.format("select user_id, training_id, training_date, training_start, training_end from %s.training \n" +
                            "left outer join %s.user using(user_id)\n" +
                            "where email is null order by training_date, training_start asc ;", schema, schema);
                    break;
                case "training_start":
                    query = String.format("select user_id, training_id, training_date, training_start, training_end from %s.training \n" +
                            "left outer join %s.user using(user_id)\n" +
                            "where email is null order by training_start ;", schema, schema);
                    break;
                case "training_end":
                    query = String.format("select user_id, training_id, training_date, training_start, training_end from %s.training \n" +
                            "left outer join %s.user using(user_id)\n" +
                            "where email is null order by training_end, training_start asc ;", schema, schema);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + filter);
            }
            statementInsert = connection.prepareStatement(query);
            ResultSet resultSet = statementInsert.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("training_id");
                LocalDate date = resultSet.getDate("training_date").toLocalDate();
                LocalTime start = resultSet.getTime("training_start").toLocalTime();
                LocalTime end = resultSet.getTime("training_end").toLocalTime();

                int user_id = resultSet.getInt("user_id");
                Training training = new Training(id, date, start, end, user_id);
                lijst.add(training);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lijst;
    }


    @Override
    public ArrayList<Training> searchByDate(LocalDate date, User user) {
        trainings.clear();
        String query = String.format("SELECT * from %s.training where training_date = ? and user_id = ?", schema);
        ArrayList<Training> foundTrainings = new ArrayList<Training>();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, Date.valueOf(date));
            preparedStatement.setInt(2, user.getUserid());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                //int id = resultSet.getInt("training_id");
                //Training training = trainings.get(id);
                int id = resultSet.getInt("training_id");
                LocalDate date1 = resultSet.getDate("training_date").toLocalDate();
                LocalTime start = resultSet.getTime("training_start").toLocalTime();
                LocalTime end = resultSet.getTime("training_end").toLocalTime();

                int user_id = resultSet.getInt("user_id");
                Training training = new Training(id, date1, start, end, user_id);
                foundTrainings.add(training);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return foundTrainings;
    }

    @Override
    public void update(Training training, User user) {
        if (training == null) {
            throw new DomainException("");
        }
        int trainingid = training.getTrainingId();
        String query = String.format("update %s.training set training_date = ?, training_start = ?, training_end = ? where training_id = ?", schema);
        List<Training> list = getAll(user);
        for (Training t : list) {
            if (training.getDate().equals(t.getDate()) && training.getStart().equals(t.getStart()) && training.getEnd().equals(t.getEnd()) && training.getTrainingId() != (t.getTrainingId())) {
                throw new DomainException("Training already exists");
            }
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, Date.valueOf(training.getDate()));
            preparedStatement.setTime(2, Time.valueOf(training.getStart()));
            preparedStatement.setTime(3, Time.valueOf(training.getEnd()));
            preparedStatement.setInt(4, trainingid);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(int trainingid) {
        String query = String.format("delete from %s.training where training_id = ?", schema);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, trainingid);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
