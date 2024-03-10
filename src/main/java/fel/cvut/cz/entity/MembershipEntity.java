package fel.cvut.cz.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "membership", schema = "public", catalog = "hollepat")
@IdClass(MembershipEntityPK.class)
public class MembershipEntity {
    @Id
    @Column(name = "type", nullable = false, length = 120)
    private String type;
    @Id
    @ManyToOne
    @JoinColumn(name = "customer")
    private CustomerEntity customer;
    @Id
    @OneToOne
    @JoinColumn(name = "invoice")
    private InvoiceEntity invoice;
    @Basic
    @Column(name = "expiredate", nullable = false)
    private Date expiredate;
    @Basic
    @Column(name = "cost", nullable = true, precision = 10, scale = 2)
    private BigDecimal cost;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public InvoiceEntity getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceEntity invoice) {
        this.invoice = invoice;
    }

    public Date getExpiredate() {
        return expiredate;
    }

    public void setExpiredate(Date expiredate) {
        this.expiredate = expiredate;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MembershipEntity that = (MembershipEntity) o;

        if (invoice != that.invoice) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (customer != null ? !customer.equals(that.customer) : that.customer != null) return false;
        if (expiredate != null ? !expiredate.equals(that.expiredate) : that.expiredate != null) return false;
        if (cost != null ? !cost.equals(that.cost) : that.cost != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        result = 31 * result + (invoice != null ? invoice.hashCode() : 0);
        result = 31 * result + (expiredate != null ? expiredate.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        return result;
    }
}
