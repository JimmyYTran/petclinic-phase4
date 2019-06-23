package com.example.petclinic.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity(name = "Visit")
@Table(name = "visit")
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date dateOfVisit;
    private String description;

    // Lazy fetch is better for performance than eager
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    // TODO implement many to many
    //private List<Vet> vets;

    protected Visit() {

    }

    public Visit(Date dateOfVisit, String description) {
        this.dateOfVisit = dateOfVisit;
        this.description = description;
    }

    public Date getDateOfVisit() {
        return dateOfVisit;
    }

    public void setDateOfVisit(Date dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visit visit = (Visit) o;
        return Objects.equals(id, visit.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Visit{");
        sb.append("id=").append(id);
        sb.append(", dateOfVisit=").append(dateOfVisit);
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static VisitBuilder builder() {
        return new VisitBuilder();
    }

    public static final class VisitBuilder {

        private Visit visit;

        private VisitBuilder() {
            visit = new Visit();
        }

        public VisitBuilder withDateOfVisit(Date dateOfVisit) {
            visit.setDateOfVisit(dateOfVisit);
            return this;
        }

        public VisitBuilder withDescription(String description) {
            visit.setDescription(description);
            return this;
        }

        public Visit build() {
            return visit;
        }
    }
}
