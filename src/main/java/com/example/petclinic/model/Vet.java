package com.example.petclinic.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.*;

@Entity(name = "Vet")
@Table(name = "vet")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Vet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ElementCollection(targetClass = Speciality.class)
    @Enumerated(EnumType.ORDINAL)
    @CollectionTable(name = "specialities")
    @Column(name = "speciality")
    private List<Speciality> specialities = new ArrayList<>();

    @ManyToMany(mappedBy = "vets")
    @JsonIgnoreProperties({"vets", "hibernateLazyInitializer"})
    private Set<Visit> visits = new HashSet<>();

    protected Vet() {

    }

    public Vet(String name, List<Speciality> specialities) {
        this.name = name;
        this.specialities = specialities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Speciality> getSpecialities() {
        return specialities;
    }

    public void addSpeciality(Speciality speciality) {
        this.specialities.add(speciality);
    }

    // Update relationship between this Vet and visits
    public void addVisit(Visit visit) {

        addVisit(visit, true);
    }

    public void addVisit(Visit visit, Boolean updateRelationship) {

        this.visits.add(visit);
        if(updateRelationship) {
            visit.addVet(this, false);
        }
    }

    public void removeVisit(Visit visit) {
        removeVisit(visit, true);
    }

    public void removeVisit(Visit visit, Boolean updateRelationship) {

        this.visits.remove(visit);
        if(updateRelationship) {
            visit.removeVet(this, false);
        }
    }

    public Set<Visit> getVisits() {

        return this.visits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vet vet = (Vet) o;
        return Objects.equals(id, vet.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Vet{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        //sb.append(", specialities=").append(specialities);
        sb.append('}');
        return sb.toString();
    }

    public static VetBuilder builder() {
        return new VetBuilder();
    }

    public static final class VetBuilder {

        private Vet vet;

        private VetBuilder() {
            vet = new Vet();
        }

        public VetBuilder withName(String name) {
            vet.setName(name);
            return this;
        }

        public VetBuilder withSpeciality(Speciality speciality) {
            vet.addSpeciality(speciality);
            return this;
        }

        public VetBuilder withVisit(Visit visit) {
            vet.addVisit(visit);
            visit.getVets().add(vet);
            return this;
        }

        public Vet build() {
            return vet;
        }
    }
}
