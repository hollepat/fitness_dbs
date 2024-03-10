package fel.cvut.cz.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "workoutclass", schema = "public", catalog = "hollepat",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name", "day", "time", "teacher"}))
public class WorkoutclassEntity {
    @Id
    @Column(name = "id", nullable = false)     // generate sequence
    private int id;
    @Basic
    @Column(name = "name", length = 120)
    private String name;
    @Basic
    @Column(name = "day")
    private Date day;
    @Basic
    @Column(name = "time")
    private Time time;
    @ManyToOne
    @JoinColumn(name = "teacher")
    private TrainerEntity teacher;
    @Basic
    @Column(name = "capacity", nullable = false)
    private int capacity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public TrainerEntity getTeacher() {
        return teacher;
    }

    public void setTeacher(TrainerEntity teacher) {
        this.teacher = teacher;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkoutclassEntity that = (WorkoutclassEntity) o;

        if (id != that.id) return false;
        if (capacity != that.capacity) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (day != null ? !day.equals(that.day) : that.day != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (teacher != null ? !teacher.equals(that.teacher) : that.teacher != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (day != null ? day.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (teacher != null ? teacher.hashCode() : 0);
        result = 31 * result + capacity;
        return result;
    }

    @Override
    public String toString() {
        return "WorkoutclassEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", day=" + day +
                ", time=" + time +
                ", teacher=" + teacher +
                ", capacity=" + capacity +
                '}';
    }
}
