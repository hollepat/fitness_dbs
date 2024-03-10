package fel.cvut.cz.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "person", schema = "public", catalog = "hollepat")
@Inheritance(strategy = InheritanceType.JOINED)
public class PersonEntity {
    @Id
    @Column(name = "nationalid", nullable = false, length = 11)
    private String nationalid;
    @Basic
    @Column(name = "phone", unique = true, length = 11)
    private String phone;
    @Basic
    @Column(name = "birth")
    private Date birth = null;
    @Basic
    @Column(name = "firstname", nullable = false, length = 120)
    private String firstname;
    @Basic
    @Column(name = "lastname", nullable = false, length = 120)
    private String lastname;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "city")),
            @AttributeOverride(name = "street", column = @Column(name = "street")),
            @AttributeOverride(name = "postalcode", column = @Column(name = "postalcode"))
    })
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getNationalid() {
        return nationalid;
    }

    public void setNationalid(String nationalid) {
        this.nationalid = nationalid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonEntity that = (PersonEntity) o;

        if (nationalid != null ? !nationalid.equals(that.nationalid) : that.nationalid != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (birth != null ? !birth.equals(that.birth) : that.birth != null) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nationalid != null ? nationalid.hashCode() : 0;
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (birth != null ? birth.hashCode() : 0);
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PersonEntity{" +
                "nationalid='" + nationalid + '\'' +
                ", phone='" + phone + '\'' +
                ", birth=" + birth +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address=" + address +
                '}';
    }
}
