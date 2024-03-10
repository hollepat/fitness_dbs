package fel.cvut.cz.entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "trainer", schema = "public", catalog = "hollepat")
public class TrainerEntity extends PersonEntity{
    @Id
    @Column(name = "nationalid", nullable = false, length = 11)
    private String nationalid;

    public String getNationalid() {
        return nationalid;
    }

    public void setNationalid(String nationalid) {
        this.nationalid = nationalid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrainerEntity that = (TrainerEntity) o;

        if (nationalid != null ? !nationalid.equals(that.nationalid) : that.nationalid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return nationalid != null ? nationalid.hashCode() : 0;
    }

}
