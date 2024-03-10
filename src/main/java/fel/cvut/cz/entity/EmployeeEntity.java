package fel.cvut.cz.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
@Entity
@Table(name = "employee", schema = "public", catalog = "hollepat")
public class EmployeeEntity extends PersonEntity{
    @Id
    @Column(name = "nationalid", nullable = false, length = 11)
    private String nationalid;
    @Basic
    @Column(name = "contractnumber", unique = true)
    private Integer contractnumber;
    @Basic
    @Column(name = "workposition", length = 120)
    private String workposition = null;
    @Basic
    @Column(name = "salary", nullable = false, precision = 10, scale = 2)
    private BigDecimal salary;

    public String getNationalid() {
        return nationalid;
    }

    public void setNationalid(String nationalid) {
        this.nationalid = nationalid;
    }

    public Integer getContractnumber() {
        return contractnumber;
    }

    public void setContractnumber(Integer contractnumber) {
        this.contractnumber = contractnumber;
    }

    public String getWorkposition() {
        return workposition;
    }

    public void setWorkposition(String workposition) {
        this.workposition = workposition;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeEntity that = (EmployeeEntity) o;

        if (nationalid != null ? !nationalid.equals(that.nationalid) : that.nationalid != null) return false;
        if (contractnumber != null ? !contractnumber.equals(that.contractnumber) : that.contractnumber != null) return false;
        if (workposition != null ? !workposition.equals(that.workposition) : that.workposition != null) return false;
        if (salary != null ? !salary.equals(that.salary) : that.salary != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nationalid != null ? nationalid.hashCode() : 0;
        result = 31 * result + (contractnumber != null ? contractnumber.hashCode() : 0);
        result = 31 * result + (workposition != null ? workposition.hashCode() : 0);
        result = 31 * result + (salary != null ? salary.hashCode() : 0);
        return result;
    }
}
