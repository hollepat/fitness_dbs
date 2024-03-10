package fel.cvut.cz.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Embeddable
public class TrainerslicenseEntityPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "trainer")
    private TrainerEntity trainer;
    @Column(name = "license", nullable = false, length = 120)
    private String license;

    public TrainerEntity getTrainer() {
        return trainer;
    }

    public void setTrainer(TrainerEntity trainer) {
        this.trainer = trainer;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrainerslicenseEntityPK that = (TrainerslicenseEntityPK) o;

        if (trainer != null ? !trainer.equals(that.trainer) : that.trainer != null) return false;
        if (license != null ? !license.equals(that.license) : that.license != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = trainer != null ? trainer.hashCode() : 0;
        result = 31 * result + (license != null ? license.hashCode() : 0);
        return result;
    }
}
