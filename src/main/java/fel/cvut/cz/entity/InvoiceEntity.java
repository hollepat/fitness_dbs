package fel.cvut.cz.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "invoice", schema = "public", catalog = "hollepat")
public class InvoiceEntity {
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    @Column(name = "invoicenumber", nullable = false)
    private int invoicenumber;
    @ManyToOne
    @JoinColumn(name = "employee", nullable = false)
    private EmployeeEntity employee;
    @Basic
    @Column(name = "dateofissue")
    private Date dateofissue;
    @Basic
    @Column(name = "cost", nullable = false, scale = 2, precision = 10)
    private BigDecimal cost;

    public int getInvoicenumber() {
        return invoicenumber;
    }

    public void setInvoicenumber(int invoicenumber) {
        this.invoicenumber = invoicenumber;
    }

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }

    public Date getDateofissue() {
        return dateofissue;
    }

    public void setDateofissue(Date dateofissue) {
        this.dateofissue = dateofissue;
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

        InvoiceEntity that = (InvoiceEntity) o;

        if (invoicenumber != that.invoicenumber) return false;
        if (employee != null ? !employee.equals(that.employee) : that.employee != null) return false;
        if (dateofissue != null ? !dateofissue.equals(that.dateofissue) : that.dateofissue != null) return false;
        if (cost != null ? !cost.equals(that.cost) : that.cost != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = invoicenumber;
        result = 31 * result + (employee != null ? employee.hashCode() : 0);
        result = 31 * result + (dateofissue != null ? dateofissue.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        return result;
    }
}
