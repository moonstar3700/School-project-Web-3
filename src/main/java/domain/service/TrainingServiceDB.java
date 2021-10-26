package domain.service;

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

public class TrainingServiceDB implements TrainingService{
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
    public void add(Training training) {
        String query = String.format("insert into %s.training (training_date, training_start, training_end, user_id) values (?,?,?,?)", schema);
        List<Training> list = getAll();
        for (Training t: list){
            if (training.getDate().equals(t.getDate())&&training.getStart().equals(t.getStart())&&training.getEnd().equals(t.getEnd())){
                throw new DbException("Training already exists");
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
            throw new DbException("Training does not exist.");
        }
        return training;
    }

    @Override
    public List<Training> getAll() {
        String query = String.format("SELECT * from %s.training order by training_id asc;", schema);
        PreparedStatement statementInsert = null;
        try {
            statementInsert = connection.prepareStatement(query);
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
    public List<Training> getAllFiltered(String filter) {
        String query = String.format("SELECT * from %s.training order by ? desc;", schema);
        PreparedStatement statementInsert = null;
        int index = 1;
        List<Training> lijst = new ArrayList<>();
        try {
            statementInsert = connection.prepareStatement(query);
            statementInsert.setString(1, filter);
            ResultSet resultSet = statementInsert.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("training_id");
                LocalDate date = resultSet.getDate("training_date").toLocalDate();
                LocalTime start = resultSet.getTime("training_start").toLocalTime();
                LocalTime end = resultSet.getTime("training_end").toLocalTime();
                //int duration = (int) MINUTES.between(start, end);

                //int duration2 = resultSet.getInt("duration");
                int user_id = resultSet.getInt("user_id");
                Training training = new Training(id, date, start, end, user_id);
                //trainingsfiltered.put(index, training);
                lijst.add(training);

                index++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        for (Training t: lijst){
            System.out.println(t.getTrainingId());
            System.out.println(t.getDate());
            System.out.println(t.getStart());
            System.out.println(t.getEnd());
        }
        //return new ArrayList<Training>(trainingsfiltered.values());
        return lijst;
    }

    @Override
    public void update(Training training) {
        if (training == null){
            throw new DbException("No training given");
        }
        int trainingid = training.getTrainingId();
        String query = String.format("update %s.training set training_date = ?, training_start = ?, training_end = ? where training_id = ?", schema);
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
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
