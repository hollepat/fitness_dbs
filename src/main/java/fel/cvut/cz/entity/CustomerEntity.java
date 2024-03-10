package fel.cvut.cz.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "customer", schema = "public", catalog = "hollepat")
public class CustomerEntity extends PersonEntity{
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

        CustomerEntity that = (CustomerEntity) o;

        if (nationalid != null ? !nationalid.equals(that.nationalid) : that.nationalid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return nationalid != null ? nationalid.hashCode() : 0;
    }
}
