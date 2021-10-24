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
        this.date = date;
    }

    public void setStart(LocalTime start) {
        if (start == null) {
            throw new DomainException("No start time selected");
        }
        this.start = start;
    }

    public void setEnd(LocalTime end) {
        if (end == null) {
            throw new DomainException("No end time selected");
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
}
