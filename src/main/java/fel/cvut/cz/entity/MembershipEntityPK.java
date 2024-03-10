package fel.cvut.cz.entity;

import jakarta.persistence.*;

import java.io.Serializable;
@Embeddable
public class MembershipEntityPK implements Serializable {
    @Column(name = "type", nullable = false, length = 120)
    private String type;
    @ManyToOne
    @JoinColumn(name = "customer")
    private CustomerEntity customer;
    @OneToOne
    @JoinColumn(name = "invoice")
    private InvoiceEntity invoice;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MembershipEntityPK that = (MembershipEntityPK) o;

        if (invoice != that.invoice) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (customer != null ? !customer.equals(that.customer) : that.customer != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        result = 31 * result + (invoice != null ? invoice.hashCode() : 0);
        return result;
    }
}
