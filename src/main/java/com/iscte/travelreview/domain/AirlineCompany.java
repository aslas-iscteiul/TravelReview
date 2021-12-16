package com.iscte.travelreview.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A AirlineCompany.
 */
@Entity
@Table(name = "airline_company")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AirlineCompany implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "website")
    private String website;

    @Column(name = "email")
    private String email;

    @Column(name = "headquarters")
    private String headquarters;

    @Column(name = "foundation")
    private LocalDate foundation;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AirlineCompany id(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public AirlineCompany name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return this.website;
    }

    public AirlineCompany website(String website) {
        this.website = website;
        return this;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return this.email;
    }

    public AirlineCompany email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeadquarters() {
        return this.headquarters;
    }

    public AirlineCompany headquarters(String headquarters) {
        this.headquarters = headquarters;
        return this;
    }

    public void setHeadquarters(String headquarters) {
        this.headquarters = headquarters;
    }

    public LocalDate getFoundation() {
        return this.foundation;
    }

    public AirlineCompany foundation(LocalDate foundation) {
        this.foundation = foundation;
        return this;
    }

    public void setFoundation(LocalDate foundation) {
        this.foundation = foundation;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AirlineCompany)) {
            return false;
        }
        return id != null && id.equals(((AirlineCompany) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AirlineCompany{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", website='" + getWebsite() + "'" +
            ", email='" + getEmail() + "'" +
            ", headquarters='" + getHeadquarters() + "'" +
            ", foundation='" + getFoundation() + "'" +
            "}";
    }
}
