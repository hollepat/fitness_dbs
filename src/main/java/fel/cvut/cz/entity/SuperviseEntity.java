package fel.cvut.cz.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "supervise", schema = "public", catalog = "hollepat")
public class SuperviseEntity {
    @Id
    @ManyToOne
    @JoinColumn(name = "supervised")
    private EmployeeEntity supervised;
    @OneToOne
    @JoinColumn(name = "supervisor", nullable = false)
    private EmployeeEntity supervisor = null;

    public EmployeeEntity getSupervised() {
        return supervised;
    }

    public void setSupervised(EmployeeEntity supervised) {
        this.supervised = supervised;
    }

    public EmployeeEntity getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(EmployeeEntity supervisor) {
        this.supervisor = supervisor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SuperviseEntity that = (SuperviseEntity) o;

        if (supervised != null ? !supervised.equals(that.supervised) : that.supervised != null) return false;
        if (supervisor != null ? !supervisor.equals(that.supervisor) : that.supervisor != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = supervised != null ? supervised.hashCode() : 0;
        result = 31 * result + (supervisor != null ? supervisor.hashCode() : 0);
        return result;
    }
}
