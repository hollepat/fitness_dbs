package fel.cvut.cz.entity;

import jakarta.persistence.*;

import java.io.Serializable;
@Embeddable
public class TakeclassEntityPK implements Serializable {
    @ManyToOne
    @JoinColumn(name = "id")
    private WorkoutclassEntity id;
    @ManyToOne
    @JoinColumn(name = "customer")
    private CustomerEntity customer;

    public WorkoutclassEntity getId() {
        return id;
    }

    public void setId(WorkoutclassEntity id) {
        this.id = id;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TakeclassEntityPK that = (TakeclassEntityPK) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (customer != null ? !customer.equals(that.customer) : that.customer != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        return result;
    }
}
