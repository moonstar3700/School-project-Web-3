package domain.model;

import static java.time.temporal.ChronoUnit.MINUTES;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Training {
    private int trainingId;
    private long duration;
    private LocalDate date;
    private LocalTime start, end;
    private User creator;

    public Training(LocalDate date, LocalTime start, LocalTime end) {
        setDate(date);
        setStart(start);
        setEnd(end);
        calculateTime();
    }

    public Training(String date, String start, LocalTime end) {
        setDate(LocalDate.parse(date));
        setStart(LocalTime.parse(start));
        setEnd(end);
        calculateTime();
    }

    public void calculateTime(){
        this.duration = MINUTES.between(getStart(), getEnd());
    }

    public int getTrainingId() {
        return trainingId;
    }

    public long getDuration() {
        return duration;
    }

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
        this.date = date;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public void setCreator(User creator) {
        if (creator == null) {
            throw new DomainException("No creator selected");
        }
        this.creator = creator;
    }
}
