package domain.service;

import domain.model.Training;
import domain.model.User;
import util.DBConnectionService;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.time.temporal.ChronoUnit.MINUTES;

public class TrainingServiceDB implements TrainingService{
    private final Map<Integer, Training> trainings = new HashMap<Integer, Training>();
    //private ArrayList<Integer> durationsArray = new ArrayList<>();
    private Map<Integer,Integer> durations = new HashMap<>();
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
        String query = String.format("SELECT * from %s.training order by training_id;", schema);
        PreparedStatement statementInsert = null;

        try {
            statementInsert = connection.prepareStatement(query);
            ResultSet resultSet = statementInsert.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("training_id");
                LocalDate date = resultSet.getDate("training_date").toLocalDate();
                LocalTime start = resultSet.getTime("training_start").toLocalTime();
                LocalTime end = resultSet.getTime("training_end").toLocalTime();
                int duration = (int) MINUTES.between(start, end);

                //int duration2 = resultSet.getInt("duration");
                int user_id = resultSet.getInt("user_id");
                Training training = new Training(id, date, start, end, user_id);
                durations.put(id, duration);
                trainings.put(id, training);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new ArrayList<Training>(trainings.values());
    }

    @Override
    public ArrayList<Integer> getDurations(){
        return new ArrayList<Integer>(durations.values());
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
