package fel.cvut.cz.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "takeclass", schema = "public", catalog = "hollepat")
@IdClass(TakeclassEntityPK.class)
public class TakeclassEntity {
    @Id
    @ManyToOne
    @JoinColumn(name = "id")
    private WorkoutclassEntity id;
    @Id
    @ManyToOne
    @JoinColumn(name = "customer")
    private CustomerEntity customer;
    @Basic
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TakeclassEntity that = (TakeclassEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (customer != null ? !customer.equals(that.customer) : that.customer != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
