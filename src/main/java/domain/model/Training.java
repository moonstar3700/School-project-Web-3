package domain.model;

import static java.time.temporal.ChronoUnit.MINUTES;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Training {
    private int trainingId;
    //private int duration;
    private int userID;
    private LocalDate date;
    private LocalTime start, end;
    private User creator;
    private final static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Training(LocalDate date, LocalTime start, LocalTime end) {
        setDate(date);
        setStart(start);
        setEnd(end);
        //calculateTime();
    }

    public Training(int id, LocalDate date, LocalTime start, LocalTime end, int userID){
        this(date, start, end);
        setTrainingId(id);
        //this.duration = duration;
        this.userID = userID;
    }

    public Training(String date, String start, LocalTime end) {
        setDate(LocalDate.parse(date));
        setStart(LocalTime.parse(start));
        setEnd(end);
        //calculateTime();
    }

    public Training(){

    }

    /*public void calculateTime(){
        this.duration = (int) MINUTES.between(getStart(), getEnd());
    }*/

    public int getTrainingId() {
        return trainingId;
    }

    /*public int getDuration() {
        return duration;
    }*/

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public User getCreator() {
        return creator;
    }

    public void setTrainingId(int trainingId) {
        this.trainingId = trainingId;
    }

    public void setDate(LocalDate date) {
        if (date == null) {
            throw new DomainException("No date selected");
        }
        if (date.isAfter(LocalDate.now())){
            throw new DomainException("Date can't be in the future");
        }
        this.date = date;
    }

    public void forceDate(LocalDate date){
        this.date = date;
    }

    public void setStart(LocalTime start) {
        if (start == null) {
            throw new DomainException("No start time selected");
        }
        if (getDate().isEqual(LocalDate.now())){
            if (start.isAfter(LocalTime.now())){
                throw new DomainException("Start time can't be in the future");
            }
        }
        this.start = start;
    }

    public void setEnd(LocalTime end) {
        if (end == null) {
            throw new DomainException("No end time selected");
        }
        if (getDate().isEqual(LocalDate.now())){
            if (end.isBefore(this.start) || end.equals(this.start)){
                throw new DomainException("End time must come after start time");
            }
        }
        this.end = end;
    }

    public void setCreator(User creator) {
        if (creator == null) {
            throw new DomainException("No creator selected");
        }
        this.creator = creator;
    }
    public void setCreatorId(User user){
        if (creator == null) {
            throw new DomainException("No creator selected");
        }
        this.userID = user.getUserid();
    }

    public int getUserID() {
        return userID;
    }

    public void checkTrainingMoment(Training training){
        if (getDate().isEqual(training.getDate())){
            if ((training.getStart().isAfter(getStart()) && training.getStart().isBefore(getEnd())) || (training.getEnd().isAfter(getStart()) && training.getEnd().isBefore(getEnd())) || training.getStart().isBefore(getStart()) && training.getEnd().isAfter(getEnd())){
                throw new DomainException("Other training already exists in this timeframe");
            } // more checks if start or end is the same
        }
    }
}
